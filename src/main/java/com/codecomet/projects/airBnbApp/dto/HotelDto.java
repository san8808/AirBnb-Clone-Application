package com.codecomet.projects.airBnbApp.dto;

import com.codecomet.projects.airBnbApp.entity.HotelContactInfo;
import lombok.Data;
import java.util.List;

@Data
public class HotelDto {

    private Long id;
    private String name;
    private String city;
    private List<String> photos;
    private List<String> amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;
}
