package com.barberback.model.dto;

public record AdminDTOResponse(
        Long id,
        String name,
        String phone,
        String email
) {
}
