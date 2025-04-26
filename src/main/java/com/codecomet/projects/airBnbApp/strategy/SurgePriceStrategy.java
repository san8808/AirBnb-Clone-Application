package com.codecomet.projects.airBnbApp.strategy;

import com.codecomet.projects.airBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class SurgePriceStrategy  implements  PricingStrategy{

    private final PricingStrategy wrappedPrice;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return wrappedPrice.calculatePrice(inventory).multiply(inventory.getSurgeFactor());
    }
}
