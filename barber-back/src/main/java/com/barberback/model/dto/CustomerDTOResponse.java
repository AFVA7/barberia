package com.barberback.model.dto;

public record CustomerDTOResponse(
        Long id,
        String name,
        String phone,
        String email
) {
}
