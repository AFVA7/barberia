package com.barberback.service.mapper;

import com.barberback.model.Appointment;
import com.barberback.model.Hairdresser;
import com.barberback.model.dto.AppointmentDTOResponse;
import com.barberback.model.dto.HairdresserDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class HairdresserDTOMapper implements Function<Hairdresser, HairdresserDTOResponse> {
    @Autowired
    private AppointmentDTOMapper appointmentDTOMapper;

    @Override
    public HairdresserDTOResponse apply(Hairdresser hairdresser) {
        return new HairdresserDTOResponse(
                hairdresser.getId(),
                hairdresser.getName(),
                hairdresser.getLastName(),
                hairdresser.getPhone(),
                hairdresser.getEmail(),
                hairdresser.getEmployeeCode(),
                appointmentDTOResponseSet(hairdresser.getAppointments())
        );
    }

    private Set<String> appointmentDTOResponseSet(Set<Appointment> appointments){
        if(appointments!=null){
            Set<String> ids = new HashSet<>();
            appointments.forEach(appointment -> {
                ids.add(String.valueOf(appointment.getId()));
            });
            return ids;
        }else{
            return null;
        }
    }
}
