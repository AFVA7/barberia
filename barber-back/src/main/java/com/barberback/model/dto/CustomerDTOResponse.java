package com.barberback.model.dto;

public record CustomerDTOResponse(
        Long id,
        String name,
        String lastName,
        String phone,
        String email
) {
}
