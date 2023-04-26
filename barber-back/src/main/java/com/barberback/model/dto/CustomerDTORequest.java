package com.barberback.model.dto;

public record CustomerDTORequest(
         String name,
         String lastName,
         String phone,
         String email,
         UserDTORequest UserDTORequest
) {
}
