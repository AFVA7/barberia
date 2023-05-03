package com.barberback.service;

import com.barberback.model.Appointment;
import com.barberback.model.dto.AppointmentDTORequest;
import com.barberback.model.dto.AppointmentDTOResponse;
import com.barberback.model.dto.CustomerDTOResponse;
import com.barberback.model.dto.HairdresserDTOResponse;

import java.util.Set;

public interface IAppointmentService {
    public AppointmentDTOResponse save(AppointmentDTORequest appointmentDTORequest);
    public AppointmentDTOResponse update(Long id, AppointmentDTORequest appointmentDTORequest);
    public AppointmentDTOResponse changeHairdresser(Long id, HairdresserDTOResponse hairdresserDTOResponse);
    public AppointmentDTOResponse changeCustomer(Long id, CustomerDTOResponse customerDTOResponse);
    public AppointmentDTOResponse findById(Long id);
    public Appointment findAppointmentById(Long id);
    public Set<AppointmentDTOResponse> findAll();
    public boolean remove(Long id);
}
