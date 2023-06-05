package com.barberback.service.mapper;

import com.barberback.model.Appointment;
import com.barberback.model.Customer;
import com.barberback.model.Record;
import com.barberback.model.dto.AppointmentDTOResponse;
import com.barberback.model.dto.CustomerDTOResponse;
import com.barberback.model.dto.RecordDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RecordDTOMapper implements Function<Record, RecordDTOResponse> {
    @Autowired
    private CustomerDTOMapper customerDTOMapper;
    @Autowired
    private AppointmentDTOMapper appointmentDTOMapper;

    @Override
    public RecordDTOResponse apply(Record record) {
        return new RecordDTOResponse(
                record.getId(),
                appointment(record.getAppointments())
                //,
                //customer(record.getCustomer())
        );
    }
    /*private CustomerDTOResponse customer(Customer customer){
        return customer!=null ? customerDTOMapper.apply(customer) : null;
    }*/
    private Set<AppointmentDTOResponse> appointment(Set<Appointment> appointments){
        if(appointments!=null){
            return appointments.stream().map(appointment -> {
                return appointmentDTOMapper.apply(appointment);
            }).collect(Collectors.toSet());
        }else{
            return null;
        }
    }
}
