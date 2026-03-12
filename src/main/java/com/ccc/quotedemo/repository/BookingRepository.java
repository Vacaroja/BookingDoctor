package com.ccc.quotedemo.repository;

import com.ccc.quotedemo.model.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
    Page<BookingEntity> findByIsActiveTrue(Pageable pageable);
    Optional<BookingEntity> findByIdBookingAndIsActiveTrue(Long id);
}
