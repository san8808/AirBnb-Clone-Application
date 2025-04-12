package com.codecomet.projects.airBnbApp.dto;

import com.codecomet.projects.airBnbApp.entity.Room;
import lombok.Data;

import java.util.List;

@Data
public class HotelInfoDto {

    private HotelDto hotel;
    private List<RoomDto> rooms;

}
