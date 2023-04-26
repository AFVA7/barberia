package com.barberback.model.dto;

import com.barberback.model.AppointmentStatus;

import java.util.Date;

public record AppointmentDTORequest(
        CustomerDTOResponse customer,
        HairdresserDTOResponse hairdresser,
        Date date,
        String status,
        PaymentDTORequest PaymentDTORequest
) {
}
