package com.ccc.quotedemo.repository;

import com.ccc.quotedemo.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<PatientEntity,Long> {
}
