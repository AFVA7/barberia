package com.barberback.service.mapper;

import com.barberback.model.Admin;
import com.barberback.model.dto.AdminDTOResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class AdminDTOMapper implements Function<Admin, AdminDTOResponse> {
    @Override
    public AdminDTOResponse apply(Admin admin) {
        return new AdminDTOResponse(
                admin.getId(),
                admin.getName(),
                admin.getPhone(),
                admin.getEmail()
        );
    }
}
