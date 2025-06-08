package com.codecomet.projects.airBnbApp.services;

import com.codecomet.projects.airBnbApp.dto.BookingDto;
import com.codecomet.projects.airBnbApp.dto.BookingRequest;
import com.codecomet.projects.airBnbApp.dto.GuestDto;
import com.codecomet.projects.airBnbApp.dto.HotelReportDto;
import com.stripe.model.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId,List<GuestDto> guestDtoList);

    String initiatePayment(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    Map<String, String> getBookingStatus(Long bookingId);

    List<BookingDto> getAllBookingsByHotel(Long hotelId);

    HotelReportDto getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);

    List<BookingDto> getMyBookings();
}
