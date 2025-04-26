package com.codecomet.projects.airBnbApp.services.impl;

import com.codecomet.projects.airBnbApp.dto.HotelDto;
import com.codecomet.projects.airBnbApp.dto.HotelPriceDto;
import com.codecomet.projects.airBnbApp.dto.HotelSearchRequest;
import com.codecomet.projects.airBnbApp.entity.Hotel;
import com.codecomet.projects.airBnbApp.entity.Inventory;
import com.codecomet.projects.airBnbApp.entity.Room;
import com.codecomet.projects.airBnbApp.repositories.HotelMinPriceRepository;
import com.codecomet.projects.airBnbApp.repositories.InventoryRepository;
import com.codecomet.projects.airBnbApp.services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;
    private final HotelMinPriceRepository hotelMinPriceRepository;

    @Override
    public void initializeRoomForAYear(Room room) {

        LocalDate today= LocalDate.now();
        LocalDate endDate = today.plusYears(1);

        for(; !today.isAfter(endDate);today=today.plusDays(1)){
            Inventory inventory= Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .reservedCount(0)
                    .city(room.getHotel().getCity())
                    .inventoryDate(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.valueOf(1))
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteAllInventories(Room room) {
        inventoryRepository.deleteByRoom(room);

    }

    @Override
    public Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest) {

        log.info("Searching hotels for {} city, from {} to {}",hotelSearchRequest.getCity(),hotelSearchRequest.getStartDate(),hotelSearchRequest.getEndDate());

        Pageable pageable= PageRequest.of(hotelSearchRequest.getPage(),hotelSearchRequest.getSize());
        Long dateCount = ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(),hotelSearchRequest.getEndDate()) + 1;


//        if(dateCount >90){
//            List<Hotel> hotels =  inventoryRepository.findHotelsWithAvailableInventory(hotelSearchRequest.getCity(),hotelSearchRequest.getStartDate()
//               ,hotelSearchRequest.getEndDate(),hotelSearchRequest.getRoomsCount(),dateCount);
//
//            List<HotelDto> hotelDtos = hotels.stream()
//                    .map(h -> modelMapper.map(h, HotelDto.class))
//                    .collect(Collectors.toList());
//
//        }


        List<HotelPriceDto> hotels =  hotelMinPriceRepository.findHotelsWithAvailableInventory(hotelSearchRequest.getCity(),hotelSearchRequest.getStartDate()
                ,hotelSearchRequest.getEndDate(),hotelSearchRequest.getRoomsCount(),dateCount);


        return new PageImpl<>(hotels, pageable, hotels.size());
    }
}
