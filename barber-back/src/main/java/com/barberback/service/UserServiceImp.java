package com.barberback.service;

import com.barberback.model.Role;
import com.barberback.model._User;
import com.barberback.model.dto.UserDTORequest;
import com.barberback.model.dto.UserDTOResponse;
import com.barberback.repository._UserRepository;
import com.barberback.service.mapper._UserDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements IUserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggerFactory.class);
    @Autowired
    private _UserRepository userRepository;
    @Autowired
    private _UserDTOMapper userDTOMapper;
    @Override
    public UserDTOResponse save(UserDTORequest userDTORequest) {
        if(!userDTORequest.roles().isEmpty()){
            Set<Role> roles = userDTORequest.roles().stream().map(role->{
                if(role.equalsIgnoreCase("admin"))
                    return Role.ADMIN;
                if(role.equalsIgnoreCase("customer"))
                    return Role.CUSTOMER;
                if(role.equalsIgnoreCase("employee"))
                    return Role.EMPLOYEE;
                return null;
            }).collect(Collectors.toSet());
            _User user = new _User();
            user.setUsername(userDTORequest.username());
            user.setPwd(userDTORequest.pwd());
            user.setRoles(roles);
            LOGGER.info("USER: user created with roles");
            return userDTOMapper.apply(userRepository.save(user));
        }else{
            _User user = new _User();
            user.setUsername(userDTORequest.username());
            user.setPwd(userDTORequest.pwd());
            LOGGER.info("USER: user created without roles");
            return userDTOMapper.apply(userRepository.save(user));
        }
    }

    @Override
    public boolean removeUser(Long id) {
        _User user = userRepository.findById(id).get();
        if(user!=null){
            userRepository.delete(user);
            LOGGER.info("USER: user deleted successfully");
            return true;
        }else{
            LOGGER.error("USER: the user doesn't exist, therefore is not possible delete it");
            return false;
        }
    }

    @Override
    public UserDTOResponse findById(Long id) {
        _User user = userRepository.findById(id).get();
        if(user!=null){
            LOGGER.info("USER: user deleted successfully");
            return userDTOMapper.apply(user);
        }else{
            LOGGER.error("USER: the user doesn't exist");
            return null;
        }
    }

    @Override
    public Set<UserDTOResponse> findAllUsers() {
        List<_User> users = userRepository.findAll();
        if(users!=null){
            LOGGER.info("USER: users found successfully");
            return users.stream().map(user -> {
                return userDTOMapper.apply(user);
            }).collect(Collectors.toSet());
        }else{
            LOGGER.error("USER: there aren't users");
            return null;
        }
    }
     //TODO: maybe is possible that I don't need use this method, because customer, admin and employee are the owner of the relationship
    @Override
    public _User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserDTOResponse updateData(Long id, UserDTORequest userDTORequest) {
        _User user = userRepository.findById(id).get();
        if(user!=null){
            user.setUsername(userDTORequest.username());
            user.setPwd(userDTORequest.pwd());
            user.setRoles(changeRoles(userDTORequest.roles()));
            _User userUpdated = userRepository.save(user);
            LOGGER.info("USER: user updated successfully");
            return userDTOMapper.apply(userUpdated);
        }else {
            LOGGER.error("USER: the user doesn't exist, therefore is not possible update it");
            return null;
        }
    }

    private Set<Role> changeRoles(Set<String> roles){
      if(roles!=null && !roles.isEmpty()){
          return roles.stream().map(role->{
              if(role.equalsIgnoreCase("admin"))
                  return Role.ADMIN;
              if(role.equalsIgnoreCase("customer"))
                  return Role.CUSTOMER;
              if(role.equalsIgnoreCase("employee"))
                  return Role.EMPLOYEE;
              return null;
          }).collect(Collectors.toSet());
      }else{
          return null;
      }
    }

    @Override
    public UserDTOResponse addRole(Long id, String role) {
        _User user = userRepository.findById(id).get();
        if(user!=null){
            switch (role){
                case "ADMIN":
                    user.getRoles().add(Role.ADMIN);
                    break;
                case "EMPLOYEE":
                    user.getRoles().add(Role.EMPLOYEE);
                    break;
                case "CUSTOMER":
                    user.getRoles().add(Role.CUSTOMER);
                    break;
                default:
                    break;
            }
            _User userUpdated = userRepository.save(user);
            LOGGER.info("USER: role added to user successfully");
            return userDTOMapper.apply(userUpdated);
        }else{
            LOGGER.error("USER: user doesn't exist, therefore is not possible add a new role");
            return null;
        }
    }

    @Override
    public UserDTOResponse removeRole(Long id, String role) {
        _User user = userRepository.findById(id).get();
        if(user!=null){
          if(user.getRoles()!=null && !user.getRoles().isEmpty()){
              switch (role){
                  case "ADMIN":
                      user.getRoles().remove(Role.ADMIN);
                      break;
                  case "EMPLOYEE":
                      user.getRoles().remove(Role.EMPLOYEE);
                      break;
                  case "CUSTOMER":
                      user.getRoles().remove(Role.CUSTOMER);
                      break;
                  default:
                      break;
              }
              _User userUpdated = userRepository.save(user);
              LOGGER.info("USER: role removed to user successfully");
              return userDTOMapper.apply(userUpdated);
          }else {
              LOGGER.error("USER: user doesn't has a roles, therefore is impossible remove it");
              return null;
          }
        }else{
            LOGGER.error("USER: user doesn't exist, therefore is not possible remove a new role");
            return null;
        }
    }
}
