package com.barberback.model.dto;

import java.util.Set;

public record UserDTORequest(
        String username,
        String pwd,
        Set<String> roles
) {
}
