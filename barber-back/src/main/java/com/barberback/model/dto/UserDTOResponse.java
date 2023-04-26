package com.barberback.model.dto;

import java.util.Set;

public record UserDTOResponse(
        Long id,
        String username,
        String pwd,
        Set<String> roles
) {
}
