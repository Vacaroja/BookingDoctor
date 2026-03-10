package com.ccc.quotedemo.repository;

import com.ccc.quotedemo.model.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<PatientEntity,Long> {
    Page<PatientEntity> findByIsActiveTrue(Pageable pageable);
}
