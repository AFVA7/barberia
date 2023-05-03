package com.barberback.service;

import com.barberback.model.Appointment;
import com.barberback.model.Customer;
import com.barberback.model.Hairdresser;
import com.barberback.model._User;
import com.barberback.model.dto.AppointmentDTORequest;
import com.barberback.model.dto.AppointmentDTOResponse;
import com.barberback.model.dto.HairdresserDTORequest;
import com.barberback.model.dto.HairdresserDTOResponse;
import com.barberback.repository.HairdresserRepository;
import com.barberback.service.mapper.HairdresserDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HairdresserServiceImp implements IHairdresserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFactory.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private HairdresserRepository hairdresserRepository;
    @Autowired
    private HairdresserDTOMapper hairdresserDTOMapper;
    @Autowired
    private IAppointmentService iAppointmentService;

    @Override
    public HairdresserDTOResponse save(HairdresserDTORequest hairdresserDTORequest) {
        _User user = null;
        Long id;
        if(hairdresserDTORequest.UserDTORequest()!=null){
            id = userService.save(hairdresserDTORequest.UserDTORequest()).id();
            user = userService.findUserById(id);
        }
        Hairdresser hairdresser = new Hairdresser();
        hairdresser.setName(hairdresserDTORequest.name());
        hairdresser.setLastName(hairdresserDTORequest.lastName());
        hairdresser.setPhone(hairdresserDTORequest.phone());
        hairdresser.setEmail(hairdresserDTORequest.email());
        hairdresser.setEmployeeCode(hairdresserDTORequest.employeeCode());
        hairdresser.setUser(user);
        hairdresser.setAppointments(new HashSet<>());
        LOGGER.info("HAIRDRESSER: hairdresser created successfully");
        return hairdresserDTOMapper.apply(hairdresserRepository.save(hairdresser));
    }

    @Override
    public HairdresserDTOResponse update(Long id, HairdresserDTORequest hairdresserDTORequest) {
        Hairdresser hairdresser = hairdresserRepository.findById(id).orElse(null);
        if(hairdresser!=null){
            hairdresser.setName(hairdresserDTORequest.name());
            hairdresser.setLastName(hairdresserDTORequest.lastName());
            hairdresser.setPhone(hairdresserDTORequest.phone());
            hairdresser.setEmail(hairdresserDTORequest.email());
            hairdresser.setEmployeeCode(hairdresserDTORequest.employeeCode());
            LOGGER.info("HAIRDRESSER: hairdresser updated successfully");
            return hairdresserDTOMapper.apply(hairdresserRepository.save(hairdresser));
        }else{
            LOGGER.error("HAIRDRESSER: the hairdresser doesn't exist therefore is not possible update it");
            return null;
        }
    }

    @Override
    public HairdresserDTOResponse findById(Long id) {
        Hairdresser hairdresser = hairdresserRepository.findById(id).orElse(null);
        if(hairdresser!=null){
            LOGGER.info("HAIRDRESSER: hairdresser found successfully");
            return hairdresserDTOMapper.apply(hairdresser);
        }else{
            LOGGER.error("HAIRDRESSER: the hairdresser doesn't exist");
            return null;
        }
    }

    @Override
    public Hairdresser findHairdresserById(Long id) {
        return hairdresserRepository.findById(id).orElse(null);
    }

    @Override
    public Set<HairdresserDTOResponse> findAll() {
        List<Hairdresser> hairdressers = hairdresserRepository.findAll();
        if(hairdressers!=null){
            LOGGER.info("HAIRDRESSER: hairdressers found successfully");
            return hairdressers.stream().map(hairdresser -> {
                return hairdresserDTOMapper.apply(hairdresser);
            }).collect(Collectors.toSet());
        }else {
            LOGGER.error("HAIRDRESSER: there aren't hairdressers");
            return null;
        }
    }

    @Override
    public boolean remove(Long id) {
        Hairdresser hairdresser = hairdresserRepository.findById(id).orElse(null);
        if(hairdresser!=null){
            hairdresserRepository.delete(hairdresser);
            LOGGER.info("HAIRDRESSER: hairdresser deleted successfully");
            return true;
        }else{
            LOGGER.error("HAIRDRESSER: the hairdresser doesn't exist therefore is not possible remove it");
            return false;
        }
    }

    @Override
    public boolean addAppointment(Long id, AppointmentDTOResponse appointmentDTOResponse) {
        Hairdresser hairdresser = hairdresserRepository.findById(id).orElse(null);
        if(hairdresser!=null){
            Appointment appointment = iAppointmentService.findAppointmentById(appointmentDTOResponse.id());
            if(appointment!=null){
            AppointmentDTOResponse response =iAppointmentService.changeHairdresser(appointmentDTOResponse.id(),hairdresserDTOMapper.apply(hairdresser));
            return response!=null ? true:false;
            }else{
             return false;
            }
        }else {
            return true;
        }
    }
}
