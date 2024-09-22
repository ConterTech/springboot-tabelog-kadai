package com.nagoyameshi.nagoyameshi.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.nagoyameshi.nagoyameshi.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionRetrieveParams;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StripeWebhookController {
    private final StripeService stripeService;

    @Value("${stripe.api-key}")
    private String stripeApiKey;

    @Value("${stripe.webhook-secret}")
    private String webhookSecret;

    @PostMapping("/stripe/webhook")
    public ResponseEntity<String> webhook(@RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {
        Stripe.apiKey = stripeApiKey;
        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject();
        optionalStripeObject.ifPresent(stripeObject -> {
            Session session = (Session) stripeObject;
            SessionRetrieveParams params = SessionRetrieveParams.builder().addExpand("subscription").build();
            try {
                session = Session.retrieve(session.getId(), params, null);
            } catch (StripeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
				Map<String, String> paymentIntentObject = session.getSubscriptionObject().getMetadata();
                paymentIntentObject.entrySet().forEach(entry -> {
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                });
        });

        if ("checkout.session.completed".equals(event.getType())) {
            try {
                stripeService.processSessionCompleted(event);
            } catch (Exception e) {
                System.err.println("Error processing session completed: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
