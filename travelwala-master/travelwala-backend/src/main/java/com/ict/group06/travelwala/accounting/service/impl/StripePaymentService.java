package com.ict.group06.travelwala.accounting.service.impl;

import com.ict.group06.travelwala.accounting.exception.BadPaymentProviderException;
import com.ict.group06.travelwala.accounting.model.CreatePaymentRequest;
import com.ict.group06.travelwala.accounting.model.CreatePaymentResponse;
import com.ict.group06.travelwala.accounting.service.ICreatePayment;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StripePaymentService implements ICreatePayment {
    @Value("${stripe.api_key}")
    private String apiKey;

    @Override
    public Object pay(Object paymentDetail) {
        Stripe.apiKey = apiKey;

        Long amount = ((CreatePaymentRequest) paymentDetail).getAmount();
        String currency = ((CreatePaymentRequest) paymentDetail).getCurrency();

        PaymentIntentCreateParams params =
            PaymentIntentCreateParams
                .builder()
                .setAmount(amount)
                .setCurrency(currency)
                .setAutomaticPaymentMethods(
                    PaymentIntentCreateParams.AutomaticPaymentMethods
                        .builder()
                        .setEnabled(true)
                        .build()
                )
                .build();
        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return new CreatePaymentResponse(paymentIntent.getClientSecret(), paymentIntent.getCurrency(), paymentIntent.getAmount());
        } catch (StripeException e) {
            throw new BadPaymentProviderException(e.getMessage());
        }
    }
}
