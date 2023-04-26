package com.barberback.service;

import com.barberback.model._User;
import com.barberback.model.dto.UserDTORequest;
import com.barberback.model.dto.UserDTOResponse;

import java.util.Set;

public interface _UserService {

    public UserDTOResponse save(UserDTORequest userDTORequest);
    public boolean removeUser(Long id);
    public UserDTOResponse findById(Long id);
    public Set<UserDTOResponse> findAllUsers();
    public UserDTOResponse updateData(Long id,UserDTORequest userDTORequest);
    public UserDTOResponse addRole(Long id, String role);
    public UserDTOResponse removeRole(Long id, String role);

}
