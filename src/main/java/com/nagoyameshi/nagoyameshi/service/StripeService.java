package com.nagoyameshi.nagoyameshi.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
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
				.setSubscriptionData(new SessionCreateParams.SubscriptionData.Builder()
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
			Session session = (Session) stripeObject;
			SessionRetrieveParams params = SessionRetrieveParams.builder().addExpand("subscription").build();

			try {
				session = Session.retrieve(session.getId(), params, null);
				Map<String, String> paymentIntentObject = session.getSubscriptionObject().getMetadata();
				userService.register(paymentIntentObject);
			} catch (StripeException e) {
				e.printStackTrace();
			}
			System.out.println("サブスクの登録が成功しました。");
			System.out.println("Stripe API Version: " + event.getApiVersion());
			System.out.println("stripe-java Version: " + Stripe.VERSION);
		},
				() -> {
					System.out.println("サブスクの登録が失敗しました。");
					System.out.println("Stripe API Version: " + event.getApiVersion());
					System.out.println("stripe-java Version: " + Stripe.VERSION);
				});
	}
}
