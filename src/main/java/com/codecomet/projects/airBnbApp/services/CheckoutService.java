package com.codecomet.projects.airBnbApp.services;

import com.codecomet.projects.airBnbApp.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);
}
