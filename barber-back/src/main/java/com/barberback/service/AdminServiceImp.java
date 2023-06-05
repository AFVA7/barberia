package com.barberback.service;

import com.barberback.model.Admin;
import com.barberback.model._User;
import com.barberback.model.dto.AdminDTORequest;
import com.barberback.model.dto.AdminDTOResponse;
import com.barberback.repository.AdminRepository;
import com.barberback.service.mapper.AdminDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminServiceImp implements IAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFactory.class);
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private AdminDTOMapper adminDTOMapper;
    @Override
    public AdminDTOResponse save(AdminDTORequest adminDTORequest) {
        _User user = null;
        Long id;
        if(adminDTORequest.userDTO()!=null) {
            id = userService.save(adminDTORequest.userDTO()).id();
            user=userService.findUserById(id);
        }
        Admin admin = new Admin();
        admin.setName(adminDTORequest.name());
        admin.setLastName(adminDTORequest.lastName());
        admin.setPhone(adminDTORequest.phone());
        admin.setEmail(adminDTORequest.email());
        admin.setUser(user);
        LOGGER.info("ADMIN: admin created successfully");
       return adminDTOMapper.apply(adminRepository.save(admin));

    }

    @Override
    public AdminDTOResponse update(Long id,AdminDTOResponse adminDTOResponse) {
        Admin adminToUpdate = adminRepository.findById(id).orElse(null);
        if(adminToUpdate==null){
            LOGGER.error("ADMIN: the admin to update doesn't exist");
            return null;
        }else{
         adminToUpdate.setName(adminDTOResponse.name());
         adminToUpdate.setLastName(adminDTOResponse.lastName());
         adminToUpdate.setPhone(adminDTOResponse.phone());
         adminToUpdate.setEmail(adminDTOResponse.email());
         Admin adminUpdated = adminRepository.save(adminToUpdate);
         LOGGER.info("ADMIN: admin updated successfully");
         return adminDTOMapper.apply(adminRepository.save(adminUpdated));
        }
    }

    @Override
    public boolean remove(Long id) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if(admin==null){
            LOGGER.error("ADMIN: the admin to remove doesn't exist");
            return false;
        }else{
          LOGGER.info("ADMIN: admin remove successfully");
          return true;
        }
    }

    @Override
    public AdminDTOResponse findById(Long id) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if(admin!=null){
            LOGGER.info("ADMIN: admin found successfully");
            return adminDTOMapper.apply(admin);
        }else{
            LOGGER.error("ADMIN: the admin fetch by id doesn't exit");
            return null;
        }
    }

    @Override
    public Set<AdminDTOResponse> findAll() {
        List<Admin> admins = adminRepository.findAll();
        if(admins!=null){
            LOGGER.info("ADMIN: admins found successfully");
            return admins.stream().map(admin -> {
                return adminDTOMapper.apply(admin);
            }).collect(Collectors.toSet());
        }else {
            LOGGER.error("ADMIN: there aren't admins");
            return null;
        }
    }
}
