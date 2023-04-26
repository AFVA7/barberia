package com.barberback.model.dto;

public record AdminDTORequest (
        String name,
        String lastName,
        String phone,
        String email,
        UserDTORequest userDTO
){
}
