package com.barberback.service;

import com.barberback.model.Record;
import com.barberback.model.dto.AppointmentDTOResponse;
import com.barberback.model.dto.CustomerDTOResponse;
import com.barberback.model.dto.RecordDTORequest;
import com.barberback.model.dto.RecordDTOResponse;

import java.util.Set;

public interface IRecordService {
    public RecordDTOResponse save(RecordDTORequest recordDTORequest);
    public RecordDTOResponse findById(Long id);
    public Record findRecordById(Long id);
    public Set<RecordDTOResponse> findAll();
    public RecordDTOResponse addAppointment(Long id, AppointmentDTOResponse appointmentDTOResponse);
    public RecordDTOResponse removeAppointment(Long id, AppointmentDTOResponse appointmentDTOResponse);
    //public RecordDTOResponse updateCustomer(Long id, CustomerDTOResponse customerDTOResponse);
}
