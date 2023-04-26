package com.barberback.service.mapper;

import com.barberback.model.Role;
import com.barberback.model._User;
import com.barberback.model.dto.UserDTOResponse;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class _UserDTOMapper implements Function<_User, UserDTOResponse> {
    @Override
    public UserDTOResponse apply(_User user) {

        Set<String> roles = user.getRoles().stream().map(role -> {
            if(role==Role.ADMIN)
                return "ADMIN";
            if(role==Role.EMPLOYEE)
                return "EMPLOYEE";
            if(role==Role.CUSTOMER)
                return "CUSTOMER";
            return "";
        }).collect(Collectors.toSet());

        return new UserDTOResponse(
                user.getId(),
                user.getUsername(),
                user.getPwd(),
                roles
                );
    }
}
