package com.ccc.quotedemo.service.booking;

import com.ccc.quotedemo.dto.BookingDTO;
import com.ccc.quotedemo.exception.NotFoundException;
import com.ccc.quotedemo.exception.ResourceNotFoundException;
import com.ccc.quotedemo.mapper.BookingMapper;
import com.ccc.quotedemo.model.BookingEntity;
import com.ccc.quotedemo.model.DoctorEntity;
import com.ccc.quotedemo.model.DoctorServicesEntity;
import com.ccc.quotedemo.model.PatientEntity;
import com.ccc.quotedemo.repository.BookingRepository;
import com.ccc.quotedemo.repository.DoctorRepository;
import com.ccc.quotedemo.repository.PatientRepository;
import com.ccc.quotedemo.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingImpl implements BookingService {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    ServicesRepository servicesRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<BookingDTO> getAllBooking(Pageable pageable) {
        if (pageable == null) throw new IllegalArgumentException("Booking not valid");
        Page<BookingEntity> bookingEntities = bookingRepository.findByIsActiveTrue(pageable);
        return bookingEntities.map(BookingMapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public BookingDTO getBookingById(Long id) {
        if (id == null) throw new IllegalArgumentException("id not valid");
        BookingEntity bookingEntity = bookingRepository.findById(id).orElseThrow(()-> new NotFoundException("Booking not founded"));

        return BookingMapper.toDTO(bookingEntity);
    }

    @Transactional
    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        if (bookingDTO == null || bookingDTO.getIdServices() == null || bookingDTO.getIdDoctor() == null) throw new IllegalArgumentException("Booking or Services or Doctor not valid");
        if (doctorRepository.existsByIdDoctorAndServices_IdServices(bookingDTO.getIdDoctor(),bookingDTO.getIdServices())){
            DoctorEntity doctor = doctorRepository.findByIdDoctorAndIsActiveTrue(bookingDTO.getIdDoctor()).orElseThrow(() -> new NotFoundException("Doctor Not Founded or Inactive"));
            PatientEntity patient = patientRepository.findByIdPatientAndIsActiveTrue(bookingDTO.getIdPatient()).orElseThrow(() -> new NotFoundException("Patient Not Founded or Inactive"));
            DoctorServicesEntity servicesEntity = servicesRepository.findByIdServicesAndIsActiveTrue(bookingDTO.getIdServices()).orElseThrow(() -> new NotFoundException("Services Not Founded or Inactive"));

            BookingEntity bookingEntity = BookingMapper.toEntity(bookingDTO,patient,servicesEntity,doctor);
            return BookingMapper.toDTO(bookingRepository.save(bookingEntity));
        } else throw new ResourceNotFoundException("Doctor not has that services");

    }

    @Transactional
    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        if (id == null || bookingDTO == null) throw new IllegalArgumentException("Booking or Id not valid");
        BookingEntity bookingEntity = bookingRepository.findByIdBookingAndIsActiveTrue(id).orElseThrow(()-> new NotFoundException("Booking Not Found"));
        bookingEntity.setInitDate(bookingDTO.getInitDate());
        bookingEntity.setEndDate(bookingDTO.getEndDate());
        bookingEntity.setStatus(bookingDTO.getStatus());
        return BookingMapper.toDTO(bookingRepository.save(bookingEntity));
    }

    @Transactional
    @Override
    public void deleteBooking(Long id) {
        if (id != null){
            BookingEntity bookingEntity = bookingRepository.findByIdBookingAndIsActiveTrue(id).orElseThrow(()-> new NotFoundException("Booking Not Found"));
            bookingEntity.setIsActive(false);
            bookingRepository.save(bookingEntity);

        }else throw new IllegalArgumentException("Id Not Valid");
    }
}
