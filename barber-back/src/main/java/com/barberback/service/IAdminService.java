package com.barberback.service;

import com.barberback.model.dto.AdminDTORequest;
import com.barberback.model.dto.AdminDTOResponse;

import java.util.Set;

public interface IAdminService {
    public AdminDTOResponse save(AdminDTORequest adminDTORequest);
    public AdminDTOResponse update(Long id,AdminDTOResponse adminDTOResponse);
    public boolean remove(Long id);
    public AdminDTOResponse findById(Long id);
    public Set<AdminDTOResponse> findAll();

}
