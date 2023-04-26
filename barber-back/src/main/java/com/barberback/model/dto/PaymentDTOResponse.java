package com.barberback.model.dto;

import java.util.Date;

public record PaymentDTOResponse(
        Long id,
        CustomerDTOResponse customer,
        String paymentMethod,
        String paymentStatus,
        Date date
) {
}
