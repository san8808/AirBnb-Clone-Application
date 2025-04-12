package com.codecomet.projects.airBnbApp.controllers;

import com.codecomet.projects.airBnbApp.dto.BookingDto;
import com.codecomet.projects.airBnbApp.dto.BookingRequest;
import com.codecomet.projects.airBnbApp.dto.GuestDto;
import com.codecomet.projects.airBnbApp.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

    private final BookingService bookingService;


    @PostMapping
    public ResponseEntity<BookingDto> initialiseBooking(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok(bookingService.initialiseBooking(bookingRequest));
    }

    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingDto> addGuests(@PathVariable Long bookingId,@RequestBody List<GuestDto> guestDtoList){
        return ResponseEntity.ok(bookingService.addGuests(bookingId,guestDtoList));

    }
}
