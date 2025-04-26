package com.codecomet.projects.airBnbApp.services;

import com.codecomet.projects.airBnbApp.dto.HotelDto;
import com.codecomet.projects.airBnbApp.dto.HotelPriceDto;
import com.codecomet.projects.airBnbApp.dto.HotelSearchRequest;
import com.codecomet.projects.airBnbApp.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
