package com.ccc.quotedemo.repository;

import com.ccc.quotedemo.model.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {

    @Query("SELECT s FROM ScheduleEntity s " +
            "LEFT JOIN FETCH s.idDoctor " +
            "WHERE s.idDoctor.id = :id " + // Accedemos al ID del objeto doctor
            "AND s.isActive = true")       // Filtramos por activos
    List<ScheduleEntity> findAllActiveSchedulesByDoctorId(@Param("id") Long id);

    List<ScheduleEntity> findByIsActiveTrue();



}
