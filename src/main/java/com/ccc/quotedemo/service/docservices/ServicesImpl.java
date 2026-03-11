package com.ccc.quotedemo.service.docservices;

import com.ccc.quotedemo.dto.ServicesDTO;
import com.ccc.quotedemo.exception.NotFoundException;
import com.ccc.quotedemo.mapper.Mapper;
import com.ccc.quotedemo.model.DoctorServicesEntity;
import com.ccc.quotedemo.repository.ServicesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServicesImpl implements ServicesService{

    @Autowired
    ServicesRepository servicesRepository;

    @Transactional
    @Override
    public List<ServicesDTO> getServices() {
        List<DoctorServicesEntity> services = servicesRepository.findByIsActiveTrue();
        return services.stream().map(Mapper::toDto).toList();
    }

    @Transactional
    @Override
    public ServicesDTO getServiceById(Long id) {
        if (id == null) throw new IllegalArgumentException("Services or Id not valid");
        DoctorServicesEntity service = servicesRepository.findById(id).orElseThrow(() -> new NotFoundException("Services Not Found"));

        return Mapper.toDto(service);
    }

    @Transactional
    @Override
    public ServicesDTO createService(ServicesDTO service) {
        if (service == null) throw new IllegalArgumentException("Services or Id not valid");
        DoctorServicesEntity serv = servicesRepository.save(Mapper.toEntity(service));
        
        return Mapper.toDto(serv);
    }

    @Transactional
    @Override
    public ServicesDTO updateService(Long id, ServicesDTO service) {
        if (service == null || id == null) throw new IllegalArgumentException("Services or Id not valid");
        DoctorServicesEntity founded = servicesRepository.findById(id).orElseThrow(() -> new NotFoundException("Services Not Founded for Update"));
        founded.setName(service.getName());
        founded.setPrice(service.getPrice());
        founded.setDuration(service.getDuration());
        
        return Mapper.toDto(servicesRepository.save(founded));
    }

    @Transactional
    @Override
    public void deleteService(Long id) {
        if (id != null) {
            DoctorServicesEntity service = servicesRepository.findById(id).orElseThrow(() -> new NotFoundException("Services Not Found"));
            service.setIsActive(false);
            servicesRepository.save(service);
        }else throw new IllegalArgumentException("Services or Id not valid");


    }
}
