package com.ccc.quotedemo.controller;

import com.ccc.quotedemo.dto.ScheduleBlockoutDTO;
import com.ccc.quotedemo.service.blockouts.ScheduleBlockoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/blockout")
public class BlockOutsController {

    @Autowired
    private ScheduleBlockoutService blockoutService;

    @GetMapping
    public ResponseEntity<List<ScheduleBlockoutDTO>> getBlockout() {
        return ResponseEntity.ok(blockoutService.getAllSchedule());
    }
    @GetMapping("/doctor")
    public ResponseEntity<List<ScheduleBlockoutDTO>> getBlockoutByDoctor(@RequestParam(name = "idDoctor") Long id) {
        return ResponseEntity.ok(blockoutService.getAllByIdDoctor(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleBlockoutDTO> getBlockoutById(@PathVariable Long id){
        return ResponseEntity.ok(blockoutService.getBlockOutById(id));
    }

    @PostMapping
    public ResponseEntity<ScheduleBlockoutDTO> createBlockout(@RequestBody ScheduleBlockoutDTO blockoutDTO) {
        ScheduleBlockoutDTO blockOutCreated = blockoutService.createBlockOut(blockoutDTO);
        return ResponseEntity.created(URI.create("/api/v1/blockout" + blockOutCreated.getIdBlockOut())).body(blockOutCreated);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ScheduleBlockoutDTO> updateBlockout(@PathVariable Long id, @RequestBody ScheduleBlockoutDTO blockoutDTO) {
        return ResponseEntity.ok(blockoutService.updateBlockOut(blockoutDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlockout(@PathVariable Long id){
        blockoutService.deleteBlockOut(id);
        return ResponseEntity.noContent().build();
    }
}
