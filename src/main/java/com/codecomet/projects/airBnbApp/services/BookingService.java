package com.codecomet.projects.airBnbApp.services;

import com.codecomet.projects.airBnbApp.dto.BookingDto;
import com.codecomet.projects.airBnbApp.dto.BookingRequest;
import com.codecomet.projects.airBnbApp.dto.GuestDto;

import java.util.List;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId,List<GuestDto> guestDtoList);
}
