package com.barberback.model.dto;

import java.util.Set;

public record RecordDTORequest(
        Set<AppointmentDTOResponse> appointments,
        CustomerDTOResponse customer
) {
}
