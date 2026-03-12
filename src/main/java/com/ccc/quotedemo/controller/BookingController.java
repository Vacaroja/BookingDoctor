package com.ccc.quotedemo.controller;

import com.ccc.quotedemo.dto.BookingDTO;
import com.ccc.quotedemo.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<Page<BookingDTO>> getBooking(Pageable pageable){
        return ResponseEntity.ok(bookingService.getAllBooking(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO){
        BookingDTO created = bookingService.createBooking(bookingDTO);
        return ResponseEntity.created(URI.create("api/v1/booking" + created.getIdBooking())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@RequestBody BookingDTO bookingDTO,@PathVariable Long id){
        return ResponseEntity.ok(bookingService.updateBooking(id,bookingDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
