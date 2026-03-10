package com.ccc.quotedemo.service;

import com.ccc.quotedemo.dto.ServicesDTO;


import java.util.List;

public interface ServicesService {
    List<ServicesDTO> getServices();
    ServicesDTO getServiceById(Long id);
    ServicesDTO createService(ServicesDTO service);
    ServicesDTO updateService(Long id,ServicesDTO service);
    void deleteService(Long id);
}
