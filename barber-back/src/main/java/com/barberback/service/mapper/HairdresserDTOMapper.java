package com.barberback.service.mapper;

import com.barberback.model.Appointment;
import com.barberback.model.Hairdresser;
import com.barberback.model.dto.AppointmentDTOResponse;
import com.barberback.model.dto.HairdresserDTOResponse;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;
@Service
public class HairdresserDTOMapper implements Function<Hairdresser, HairdresserDTOResponse> {
    @Override
    public HairdresserDTOResponse apply(Hairdresser hairdresser) {
        return new HairdresserDTOResponse(
                hairdresser.getId(),
                hairdresser.getName(),
                hairdresser.getLastName(),
                hairdresser.getPhone(),
                hairdresser.getEmail(),
                hairdresser.getEmployeeCode(),
                null
        );
    }

    private Set<AppointmentDTOResponse> appointmentDTOResponseSet(Set<Appointment> appointments){
        if(appointments!=null){
            //TODO: return appointmentsDTOResponse

        }else{
            //TODO: return null
        }
        return null;
    }
}
