package com.barberback.model.dto;

import java.util.Set;

public record HairdresserDTOResponse(
        Long id,
        String name,
        String lastName,
        String phone,
        String email,
        int employeeCode,
        Set<AppointmentDTOResponse> appointments
) {
}
