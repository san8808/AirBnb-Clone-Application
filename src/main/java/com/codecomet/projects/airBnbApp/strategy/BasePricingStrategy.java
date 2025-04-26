package com.codecomet.projects.airBnbApp.strategy;

import com.codecomet.projects.airBnbApp.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public class BasePricingStrategy implements PricingStrategy{

    /*
    * this will return the base price od the room of a particular Inventory
    * */

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
