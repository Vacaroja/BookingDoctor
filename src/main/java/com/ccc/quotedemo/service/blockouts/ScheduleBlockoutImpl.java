package com.ccc.quotedemo.service.blockouts;

import com.ccc.quotedemo.dto.ScheduleBlockoutDTO;
import com.ccc.quotedemo.exception.NotFoundException;
import com.ccc.quotedemo.mapper.BlockOutsMapper;
import com.ccc.quotedemo.model.DoctorEntity;
import com.ccc.quotedemo.model.ScheduleBlockoutsEntity;
import com.ccc.quotedemo.repository.DoctorRepository;
import com.ccc.quotedemo.repository.ScheduleBlockoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleBlockoutImpl implements ScheduleBlockoutService {

    @Autowired
    ScheduleBlockoutRepository blockoutRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleBlockoutDTO> getAllSchedule() {
        List<ScheduleBlockoutsEntity> blockoutsEntities = blockoutRepository.findByIsActiveTrue();
        return blockoutsEntities.stream().map(BlockOutsMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleBlockoutDTO> getAllByIdDoctor(Long id) {
        if (id == null) throw new IllegalArgumentException("Doctor or Id not valid");
        if (blockoutRepository.existsByDoctor_IdDoctorAndIsActiveTrue(id)) {
            List<ScheduleBlockoutsEntity> blockoutsEntities = blockoutRepository.findAllByIdDoctorAndIsActive(id);
            return blockoutsEntities.stream().map(BlockOutsMapper::toDto).toList();
        } else throw new NotFoundException("Doctor not found");
    }

    @Transactional(readOnly = true)
    @Override
    public ScheduleBlockoutDTO getBlockOutById(Long id) {
        if (id == null) throw new IllegalArgumentException("Id not valid");
        ScheduleBlockoutsEntity blockouts = blockoutRepository.findById(id).orElseThrow(()-> new NotFoundException("Schedule BlockOut Not Found"));

        return BlockOutsMapper.toDto(blockouts);
    }

    @Transactional
    @Override
    public ScheduleBlockoutDTO createBlockOut(ScheduleBlockoutDTO blockoutDTO) {
        if (blockoutDTO == null || blockoutDTO.getIdDoctor() == null ) throw new IllegalArgumentException("Schedule BlockOut Or Id or Doctor Not Valid");
        DoctorEntity doctor = doctorRepository.findByIdDoctorAndIsActiveTrue(blockoutDTO.getIdDoctor()).orElseThrow(()-> new NotFoundException("Doctor not found or Inactivated"));
        ScheduleBlockoutsEntity blockoutsEntity = BlockOutsMapper.toEntity(blockoutDTO,doctor);
        return  BlockOutsMapper.toDto(blockoutRepository.save(blockoutsEntity));
    }

    @Transactional
    @Override
    public ScheduleBlockoutDTO updateBlockOut(ScheduleBlockoutDTO blockoutDTO, Long id) {
        if (blockoutDTO == null || blockoutDTO.getIdDoctor() == null ) throw new IllegalArgumentException("Schedule BlockOut Or Id or Doctor Not Valid");
        ScheduleBlockoutsEntity blockouts = blockoutRepository.findById(id).orElseThrow(()-> new NotFoundException("Schedule BlockOut Not Found"));
        blockouts.setReason(blockoutDTO.getReason());
        blockouts.setEndDate(blockoutDTO.getEndDate());
        blockouts.setInitDate(blockoutDTO.getInitDate());

        return BlockOutsMapper.toDto(blockoutRepository.save(blockouts));
    }

    @Transactional
    @Override
    public void deleteBlockOut(Long id) {
        if (id != null){
            ScheduleBlockoutsEntity blockouts = blockoutRepository.findById(id).orElseThrow(()-> new NotFoundException("Schedule BlockOut Not Found"));
            blockouts.setIsActive(false);
            blockoutRepository.save(blockouts);
        }else throw new IllegalArgumentException("Doctor or Id not valid");

    }
}
