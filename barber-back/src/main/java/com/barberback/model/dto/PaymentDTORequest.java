package com.barberback.model.dto;

import java.util.Date;

public record PaymentDTORequest(
        CustomerDTOResponse customer,
        String paymentMethod,
        String paymentStatus,
        Date date
) {
}
