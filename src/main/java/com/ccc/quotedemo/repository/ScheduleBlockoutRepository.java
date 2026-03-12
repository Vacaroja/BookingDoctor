package com.ccc.quotedemo.repository;

import com.ccc.quotedemo.model.ScheduleBlockoutsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleBlockoutRepository extends JpaRepository<ScheduleBlockoutsEntity,Long> {

    @Query("SELECT d FROM ScheduleBlockoutsEntity d LEFT JOIN FETCH d.doctor WHERE d.doctor.id =:id AND d.isActive = true")
    List<ScheduleBlockoutsEntity> findAllByIdDoctorAndIsActive(Long id);
    List<ScheduleBlockoutsEntity> findByIsActiveTrue();
    Boolean existsByDoctor_IdDoctorAndIsActiveTrue(Long idDoctor);
}
