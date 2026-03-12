package com.ccc.quotedemo.service.booking;

import com.ccc.quotedemo.dto.BookingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {
    Page<BookingDTO> getAllBooking(Pageable pageable);
    BookingDTO getBookingById(Long id);
    BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO updateBooking(Long id,BookingDTO bookingDTO);
    void deleteBooking(Long id);
}
