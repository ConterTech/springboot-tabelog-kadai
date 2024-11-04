package com.nagoyameshi.nagoyameshi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nagoyameshi.nagoyameshi.repository.RoleRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.PaymentMethod;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import com.stripe.param.SubscriptionListParams;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData.Recurring;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData.Recurring.Interval;
import com.stripe.param.checkout.SessionRetrieveParams;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StripeService {
	@Value("${stripe.api-key}")
	private String stripeApiKey;

	private final UserService userService;
	private final RoleRepository roleRepository;

	// セッションを作成し、Stripeに必要な情報を返す
	public String createStripeSession(Integer userId,
			HttpServletRequest httpServletRequest) {
		Stripe.apiKey = stripeApiKey;
		String requestUrl = new String(httpServletRequest.getRequestURL());
		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.addLineItem(
						SessionCreateParams.LineItem.builder()
								.setPriceData(
										SessionCreateParams.LineItem.PriceData
												.builder()
												.setProductData(
														SessionCreateParams.LineItem.PriceData.ProductData
																.builder()
																.setName("nagoyameshiサブスクリプション")
																.build())
												.setUnitAmount((long) 300)
												.setCurrency("jpy")
												.setRecurring(Recurring
														.builder()
														.setInterval(Interval.MONTH)
														.build())
												.build())
								.setQuantity(1L)
								.build())
				.setMode(SessionCreateParams.Mode.SUBSCRIPTION)
				.setClientReferenceId(userId.toString())
				.setSubscriptionData(SessionCreateParams.SubscriptionData.builder()
						.putMetadata("userId", userId.toString()).build())
				.setSuccessUrl(
						requestUrl.replace("", ""))
				.setCancelUrl(requestUrl.replace("/user", ""))
				.build();
		try {
			Session session = Session.create(params);
			return session.getId();
		} catch (StripeException e) {
			e.printStackTrace();
			return "";
		}
	}

	// サブスク登録
	public void processSessionCompleted(Event event) {
		Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject();

		optionalStripeObject.ifPresentOrElse(stripeObject -> {
			if (stripeObject instanceof Session session) {
				// セッションオブジェクトの処理
				SessionRetrieveParams params = SessionRetrieveParams.builder().addExpand("subscription").build();

				try {
					session = Session.retrieve(session.getId(), params, null);
					// userId格納
					Map<String, String> subscriptionObject = session.getSubscriptionObject().getMetadata();
					// 顧客ID格納
					String customerId = session.getCustomer();
					// 会員ステータスフラグ更新
					userService.register(subscriptionObject, customerId);

					System.out.println("サブスクの登録が成功しました。");
				} catch (StripeException e) {
					e.printStackTrace();
				}
			} else {

				System.out.println("受信したオブジェクトはSessionではありません。");
			}
		}, () -> {
			System.out.println("サブスクの登録が失敗しました。");
		});
	}

	// PaymentMethod取得
	public PaymentMethod getPaymentMethod(String customerId) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("customer", customerId);
			params.put("type", "card");

			PaymentMethodCollection paymentMethods = PaymentMethod.list(params);

			if (!paymentMethods.getData().isEmpty()) {
				return paymentMethods.getData().get(0);
			}
		} catch (StripeException e) {
			e.printStackTrace();
		}

		return null;
	}

	// カード情報の更新
	public void updateCard(String customerId, String paymentMethodId) throws StripeException {
		PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentMethodId);

		Map<String, Object> paramPayment = new HashMap<>();
		Map<String, Object> paramCustomer = new HashMap<>();

		paramPayment.put("customer", customerId);
		paramCustomer.put("invoice_settings", Map.of("default_payment_method", paymentMethodId));

		Customer customer = Customer.retrieve(customerId);

		paymentMethod.attach(paramPayment);
		customer.update(paramCustomer);
	}

	// サブスク解約
	public Subscription getSubscriptionId(String customerId){
		try {
			// サブスクをリストするためのパラメータ
            SubscriptionListParams params = SubscriptionListParams.builder()
                .setCustomer(customerId)
                .build();

			// リスト取得
            List<Subscription> subscriptions = Subscription.list(params).getData();
            
			for (Subscription subscription : subscriptions) {
                if ("active".equals(subscription.getStatus())) {
                    return subscription;
                }
            }
            
            return null;            
        } catch (StripeException e) { 
            e.printStackTrace();
            return null;
        }
	}

	public void cancelSubscription(Subscription subscription){
		try{
			subscription.cancel();
		}catch(StripeException e){
			e.printStackTrace();
		}
	}
}
