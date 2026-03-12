package com.ccc.quotedemo.service.doctor;

import com.ccc.quotedemo.dto.DoctorDTO;
import com.ccc.quotedemo.exception.NotFoundException;
import com.ccc.quotedemo.exception.ResourceNotFoundException;
import com.ccc.quotedemo.mapper.Mapper;
import com.ccc.quotedemo.model.DoctorEntity;
import com.ccc.quotedemo.model.DoctorServicesEntity;
import com.ccc.quotedemo.repository.DoctorRepository;
import com.ccc.quotedemo.repository.ScheduleBlockoutRepository;
import com.ccc.quotedemo.repository.ServicesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ScheduleBlockoutRepository scheduleBlockOutRepository;

    @Autowired
    private ServicesRepository servicesRepository;


    @Transactional
    @Override
    public List<DoctorDTO> getDoctors() {
        List<DoctorEntity> doctor = doctorRepository.findByIsActiveTrue();

        Set<Long> blockedIds = scheduleBlockOutRepository.findAll().stream().map(b -> b.getDoctor().getIdDoctor()).collect(Collectors.toSet());

        return doctor.stream().map(doc -> Mapper.toSummaryDto(doc,!blockedIds.contains(doc.getIdDoctor()))).toList();
    }

    @Transactional
    @Override
    public DoctorDTO getDoctorById(Long id) {
        if (id == null) throw new IllegalArgumentException("Doctor or Id not valid");
        DoctorEntity doctorEntity = doctorRepository.findByIdWithSchedules(id).orElseThrow(() -> new NotFoundException("Doctor Not Found"));
        Boolean isAvailable = !scheduleBlockOutRepository.existsByDoctor_IdDoctorAndIsActiveTrue(id);

        return Mapper.toDto(doctorEntity, isAvailable);
    }

    @Transactional
    @Override
    public DoctorDTO linkDoctorWithServices(Long idDoctor, Long idServices) {
        DoctorEntity doctorEntity = doctorRepository.findByIdDoctorAndIsActiveTrue(idDoctor).orElseThrow(() -> new NotFoundException("Doctor Not Found to link"));
        DoctorServicesEntity servicesEntity = servicesRepository.findByIdServicesAndIsActiveTrue(idServices).orElseThrow(() -> new NotFoundException("Servoces Not Founded to link"));
        doctorEntity.addServices(servicesEntity);
        Boolean isAvailable = !scheduleBlockOutRepository.existsByDoctor_IdDoctorAndIsActiveTrue(idDoctor);

        return Mapper.toDto(doctorRepository.save(doctorEntity),isAvailable);
    }

    @Transactional
    @Override
    public DoctorDTO unlinkDoctorWithServices(Long idDoctor, Long idServices) {
        DoctorEntity doctorEntity = doctorRepository.findByIdDoctorAndIsActiveTrue(idDoctor).orElseThrow(() -> new NotFoundException("Doctor Not Found to link"));
        DoctorServicesEntity servicesEntity = servicesRepository.findByIdServicesAndIsActiveTrue(idServices).orElseThrow(() -> new NotFoundException("Services Not Founded to link"));
        Boolean doctorHas = doctorRepository.existsByIdDoctorAndServices_IdServices(idDoctor,idServices);
        if (!doctorHas) throw new ResourceNotFoundException("Doctor not has the service");
        doctorEntity.deleteServices(servicesEntity);
        Boolean isAvailable = !scheduleBlockOutRepository.existsByDoctor_IdDoctorAndIsActiveTrue(idDoctor);

        return Mapper.toDto(doctorRepository.save(doctorEntity),isAvailable);
    }

    @Transactional
    @Override
    public DoctorDTO createDoctors(DoctorDTO doctorDto) {
        if (doctorDto == null) throw new IllegalArgumentException("Doctor or Id not valid");

        DoctorEntity doctorEntity = Mapper.toEntity(doctorDto);

        DoctorEntity savedEntity = doctorRepository.save(doctorEntity);

        return Mapper.toDto(savedEntity,true);
    }

    @Transactional
    @Override
    public DoctorDTO updateDoctors(Long id, DoctorDTO doctorDto) {
        if (doctorDto == null || id == null ) throw new IllegalArgumentException("Doctor or Id not valid");
        DoctorEntity foundedDoc = doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Doctor Not Found"));

        foundedDoc.setName(doctorDto.getName());
        foundedDoc.setTitle(doctorDto.getTitle());

        DoctorEntity savedEntity = doctorRepository.save(foundedDoc);
        Boolean isAvailable = !scheduleBlockOutRepository.existsByDoctor_IdDoctorAndIsActiveTrue(id);


        return Mapper.toDto(savedEntity, isAvailable);
    }
    @Transactional
    @Override
    public void deleteDoctor(Long id) {
        if (id != null){
            DoctorEntity foundedDoc = doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Doctor Not Found"));
            foundedDoc.setIsActive(false);
            doctorRepository.save(foundedDoc);
        }else throw new IllegalArgumentException("Doctor or Id not valid");
    }
}
