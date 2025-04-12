package com.codecomet.projects.airBnbApp.services.impl;

import com.codecomet.projects.airBnbApp.dto.RoomDto;
import com.codecomet.projects.airBnbApp.entity.Hotel;
import com.codecomet.projects.airBnbApp.entity.Room;
import com.codecomet.projects.airBnbApp.exception.ResourceNotFoundException;
import com.codecomet.projects.airBnbApp.repositories.HotelRepository;
import com.codecomet.projects.airBnbApp.repositories.RoomRepository;
import com.codecomet.projects.airBnbApp.services.InventoryService;
import com.codecomet.projects.airBnbApp.services.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId,RoomDto roomDto) {
        log.info("Creating room with hotel id: {}", hotelId);

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id "+hotelId+" not found. Please check the hotel details before creating room"));

        Room room = modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        if(hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);
        }

        log.info("Created new room with id: {} for hotel with id {}", room.getId(),hotelId);
        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms with hotel id: {}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id "+hotelId+" not found. Please check the hotel details before creating room"));

        log.info("Rooms fetched total count: {}",hotel.getRooms().size());
        return hotel.getRooms()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect( Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {

        log.info("Getting the room with roomId: {}",roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id "+roomId+" not found"));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room with id: {}",roomId);

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id "+roomId+" not found"));
        inventoryService.deleteAllInventories(room);
        roomRepository.deleteById(roomId);

        log.info("Room with id: "+roomId+" deleted successfully");
    }
}
