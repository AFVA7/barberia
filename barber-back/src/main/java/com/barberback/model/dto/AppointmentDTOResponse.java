package com.barberback.model.dto;

import com.barberback.model.AppointmentStatus;

import java.util.Date;

public record AppointmentDTOResponse(
        Long id,
        CustomerDTOResponse customer,
        HairdresserDTOResponse hairdresser,
        Date date,
        Date lastModification,
        AppointmentStatus status,
        PaymentDTOResponse paymentDTOResponse
) {
}
