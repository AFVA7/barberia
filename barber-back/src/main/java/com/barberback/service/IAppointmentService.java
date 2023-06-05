package com.barberback.service;

import com.barberback.model.Appointment;
import com.barberback.model.dto.*;

import java.util.Set;

public interface IAppointmentService {
    public AppointmentDTOResponse save(AppointmentDTORequest appointmentDTORequest);
    public AppointmentDTOResponse update(Long id, AppointmentDTORequest appointmentDTORequest);
    public AppointmentDTOResponse changeHairdresser(Long id, HairdresserDTOResponse hairdresserDTOResponse);
    public AppointmentDTOResponse changeCustomer(Long id, CustomerDTOResponse customerDTOResponse);
    public AppointmentDTOResponse addPayment(Long id, PaymentDTOResponse paymentDTOResponse);
    public AppointmentDTOResponse findById(Long id);
    public Set<AppointmentDTOResponse> findAppointmentsByStatus(String status);
    public Appointment findAppointmentById(Long id);
    public Set<AppointmentDTOResponse> findAll();
    public boolean remove(Long id);
}
