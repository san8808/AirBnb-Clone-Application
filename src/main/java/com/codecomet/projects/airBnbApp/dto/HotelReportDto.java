package com.codecomet.projects.airBnbApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelReportDto {

    private Long bookingCount;
    private BigDecimal hotelRevenue;
    private BigDecimal averageRevenue;


}
