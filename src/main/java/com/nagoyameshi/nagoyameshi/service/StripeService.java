package com.nagoyameshi.nagoyameshi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData.Recurring;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData.Recurring.Interval;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StripeService {
	@Value("${stripe.api-key}")
	private String stripeApiKey;

	// セッションを作成し、Stripeに必要な情報を返す
	public String createStripeSession(UserEntity user,
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
				.setSuccessUrl(
						requestUrl + "/completed")
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

	public void cancel(String subscriptionId) throws StripeException {
		Subscription subscription = Subscription.retrieve(subscriptionId);
		subscription.cancel();
	}
}
