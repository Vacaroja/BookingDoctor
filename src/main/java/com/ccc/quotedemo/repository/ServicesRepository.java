package com.ccc.quotedemo.repository;


import com.ccc.quotedemo.model.DoctorServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<DoctorServicesEntity,Long> {
    List<DoctorServicesEntity> findByIsActiveTrue();
}
