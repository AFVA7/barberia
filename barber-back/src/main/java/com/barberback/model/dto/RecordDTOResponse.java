package com.barberback.model.dto;

import java.util.Set;

public record RecordDTOResponse(
        Long id,
        Set<AppointmentDTOResponse> appointments
        //,
        //CustomerDTOResponse customer
) {
}
