package com.codecomet.projects.airBnbApp.congif;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    public StripeConfig(@Value("${stripe.secret.key}") String stripeSecretKey){
        Stripe.apiKey= stripeSecretKey;
    }
}
