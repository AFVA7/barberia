package com.barberback.model.dto;

public record AdminDTOResponse(
        Long id,
        String name,
        String lastName,
        String phone,
        String email
) {
}
