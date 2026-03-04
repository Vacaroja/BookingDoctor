package com.ccc.quotedemo.repository;

import com.ccc.quotedemo.model.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Long> {

    @Query("SELECT d FROM DoctorEntity d LEFT JOIN FETCH d.schedules WHERE d.idDoctor = :id")
    Optional<DoctorEntity> findByIdWithSchedules(@Param("id") Long id);

    List<DoctorEntity> findByIsActiveTrue();

}
