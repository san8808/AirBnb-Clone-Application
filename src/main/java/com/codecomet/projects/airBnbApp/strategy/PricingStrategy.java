package com.codecomet.projects.airBnbApp.strategy;

import com.codecomet.projects.airBnbApp.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}
