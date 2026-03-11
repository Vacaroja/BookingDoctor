package com.ccc.quotedemo.controller;

import com.ccc.quotedemo.dto.ScheduleDTO;
import com.ccc.quotedemo.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getSchedule() {
        return ResponseEntity.ok(scheduleService.getSchedule());
    }
    @GetMapping
    @RequestMapping("/doctor")
    public ResponseEntity<List<ScheduleDTO>> getScheduleByDoctor(@RequestParam(name = "idDoctor") Long id) {
        return ResponseEntity.ok(scheduleService.getScheduleByDoctor(id));
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id){
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO schedule = scheduleService.createSchedule(scheduleDTO);
        return ResponseEntity.created(URI.create("/api/v1/schedule" + schedule.getIdSchedule())).body(schedule);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, scheduleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

}
