package com.ccc.quotedemo.controller;

import com.ccc.quotedemo.dto.ServicesDTO;
import com.ccc.quotedemo.service.docservices.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/service")
public class ServicesController {

    @Autowired
    private ServicesService services;

    @GetMapping
    public ResponseEntity<List<ServicesDTO>> getDoctors() {
        return ResponseEntity.ok(services.getServices());

    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<ServicesDTO> getDoctorById(@PathVariable Long id){
        return ResponseEntity.ok(services.getServiceById(id));
    }

    @PostMapping
    public ResponseEntity<ServicesDTO> createDoctor(@RequestBody ServicesDTO ServicesDTO) {
        ServicesDTO doc = services.createService(ServicesDTO);
        return ResponseEntity.created(URI.create("/api/v1/doctor" + doc.getIdServices())).body(doc);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ServicesDTO> updateDoctor(@PathVariable Long id, @RequestBody ServicesDTO ServicesDTO) {
        return ResponseEntity.ok(services.updateService(id, ServicesDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        services.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
