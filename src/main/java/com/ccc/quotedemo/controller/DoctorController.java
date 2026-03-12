package com.ccc.quotedemo.controller;

import com.ccc.quotedemo.dto.DoctorDTO;
import com.ccc.quotedemo.service.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getDoctors() {
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        DoctorDTO doc = doctorService.createDoctors(doctorDTO);
        return ResponseEntity.created(URI.create("/api/doctor" + doc.getIdDoctor())).body(doc);
    }

    @PostMapping("/link")
    public ResponseEntity<DoctorDTO> linkDoctorWithServices(@RequestParam(name = "doctor") Long idDoctor,@RequestParam(name = "services") Long idServices){
        DoctorDTO linked = doctorService.linkDoctorWithServices(idDoctor,idServices);
        return ResponseEntity.ok(linked);
    }

    @DeleteMapping("/link")
    public ResponseEntity<DoctorDTO> unlinkDoctorWithServices(@RequestParam(name = "doctor") Long idDoctor,@RequestParam(name = "services") Long idServices){
        DoctorDTO linked = doctorService.unlinkDoctorWithServices(idDoctor,idServices);
        return ResponseEntity.ok(linked);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        return ResponseEntity.ok(doctorService.updateDoctors(id, doctorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
