package com.barberback.model.dto;

public record HairdresserDTORequest(
        String name,
        String lastName,
        String phone,
        String email,
        UserDTORequest UserDTORequest,
        int employeeCode
) {
}
