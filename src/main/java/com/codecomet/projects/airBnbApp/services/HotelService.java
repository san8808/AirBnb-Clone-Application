package com.codecomet.projects.airBnbApp.services;

import com.codecomet.projects.airBnbApp.dto.HotelDto;
import com.codecomet.projects.airBnbApp.dto.HotelInfoDto;
import com.codecomet.projects.airBnbApp.entity.Hotel;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long hotelId);

    HotelDto updateHotelById(Long hotelId,HotelDto dto);

    Boolean deleteHotelById(long id);

    void activateHotel(Long hotelId);

    HotelInfoDto getHotelInfoById(Long hotelId);
}
