package com.barberback.service;

import com.barberback.model.Hairdresser;
import com.barberback.model.dto.AppointmentDTOResponse;
import com.barberback.model.dto.HairdresserDTORequest;
import com.barberback.model.dto.HairdresserDTOResponse;

import java.util.Set;

public interface IHairdresserService {
    public HairdresserDTOResponse save(HairdresserDTORequest hairdresserDTORequest);
    public  HairdresserDTOResponse update(HairdresserDTOResponse hairdresserDTOResponse);
    public HairdresserDTOResponse findById(Long id);
    public Hairdresser findHairdresserById(Long id);
    public Set<HairdresserDTOResponse> findAll();
    public boolean remove(Long id);
//    public boolean addAppointment(Long id, AppointmentDTOResponse appointmentDTOResponse);
    public HairdresserDTOResponse addAppointment(Long id, AppointmentDTOResponse appointmentDTOResponse);
}
