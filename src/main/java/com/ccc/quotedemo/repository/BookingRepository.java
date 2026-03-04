package com.ccc.quotedemo.repository;

import com.ccc.quotedemo.model.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
}
