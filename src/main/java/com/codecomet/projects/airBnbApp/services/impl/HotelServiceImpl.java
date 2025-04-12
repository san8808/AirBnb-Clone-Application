package com.codecomet.projects.airBnbApp.services.impl;

import com.codecomet.projects.airBnbApp.dto.HotelDto;
import com.codecomet.projects.airBnbApp.dto.HotelInfoDto;
import com.codecomet.projects.airBnbApp.dto.RoomDto;
import com.codecomet.projects.airBnbApp.entity.Hotel;
import com.codecomet.projects.airBnbApp.entity.Room;
import com.codecomet.projects.airBnbApp.exception.ResourceNotFoundException;
import com.codecomet.projects.airBnbApp.repositories.HotelRepository;
import com.codecomet.projects.airBnbApp.repositories.RoomRepository;
import com.codecomet.projects.airBnbApp.services.HotelService;
import com.codecomet.projects.airBnbApp.services.InventoryService;
import com.codecomet.projects.airBnbApp.services.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name: {}", hotelDto.getName());
        Hotel hotel= modelMapper.map(hotelDto,Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created a hotel with id: {}", hotelDto.getId());
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long hotelId) {
        log.info("Getting hotel by id: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long hotelId,HotelDto hotelDto) {
        log.info("Updating the hotel with ID: {}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));

        modelMapper.map(hotelDto,hotel);
        hotel.setId(hotelId);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    @Transactional
    public Boolean deleteHotelById(long id) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+id));
        hotel.setActive(true);

        /*deleting all inventory room details for the deleted hotel*/
        for(Room room: hotel.getRooms()){
            inventoryService.deleteAllInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("Activating the hotel with ID: {}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));
        hotel.setActive(true);
        hotelRepository.save(hotel);

        log.info("Creating one year inventory for all the rooms in hotel with id: {}",hotelId);
        //assuming only do it once
        for(Room room: hotel.getRooms()){
            inventoryService.initializeRoomForAYear(room);
        }

    }

    @Override
    public HotelInfoDto getHotelInfoById(Long hotelId) {

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));

        List<RoomDto> rooms = hotel.getRooms()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect( Collectors.toList());

        HotelInfoDto hotelInfoDto = new HotelInfoDto();
        hotelInfoDto.setHotel(modelMapper.map(hotel,HotelDto.class));
        hotelInfoDto.setRooms(rooms);

        return hotelInfoDto;
    }
}
