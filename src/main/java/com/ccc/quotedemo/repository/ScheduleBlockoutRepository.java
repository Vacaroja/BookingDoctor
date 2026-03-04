package com.ccc.quotedemo.repository;

import com.ccc.quotedemo.model.ScheduleBlockoutsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleBlockoutRepository extends JpaRepository<ScheduleBlockoutsEntity,Long> {

    Boolean existsByDoctor_IdDoctor(Long idDoctor);
}
