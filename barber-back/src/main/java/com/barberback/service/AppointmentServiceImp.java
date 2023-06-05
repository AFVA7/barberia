package com.barberback.service;

import com.barberback.model.*;
import com.barberback.model.dto.*;
import com.barberback.repository.AppointmentRepository;
import com.barberback.service.mapper.AppointmentDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImp implements IAppointmentService{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFactory.class);
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private IHairdresserService iHairdresserService;
    @Autowired
    private AppointmentDTOMapper appointmentDTOMapper;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private IPaymentService iPaymentService;

    @Override
    public AppointmentDTOResponse save(AppointmentDTORequest appointmentDTORequest) {
        if(appointmentDTORequest!=null){
            Appointment appointment = new Appointment();
            if(appointmentDTORequest.customer()!=null)
                appointment.setCustomer(iCustomerService.findCustomerById(appointmentDTORequest.customer().id()));
            if(appointmentDTORequest.hairdresser()!=null)
                appointment.setHairdresser(iHairdresserService.findHairdresserById(appointmentDTORequest.hairdresser().id()));
            appointment.setDate(appointmentDTORequest.date());
            appointment.setLastModification(appointmentDTORequest.date()); //the last time it was update (it change every time)
            appointment.setStatus(status(appointmentDTORequest.status()));
            return appointmentDTOMapper.apply(appointmentRepository.save(appointment));

        }else{
            return null;
        }
    }

    private AppointmentStatus status(String status){
        switch (status){
            case "ON_HOLD":
                return AppointmentStatus.ON_HOLD;
            case "IN_PROGRESS":
                return AppointmentStatus.IN_PROCESS;
            case "COMPLETED":
                return AppointmentStatus.COMPLETED;
            default:
                return null;
        }
    }

    /**
     * method where we change the customer, hairdresser, date or status
     * @param appointmentDTORequest
     * @return
     */
    @Override
    public AppointmentDTOResponse update(Long id,AppointmentDTORequest appointmentDTORequest) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if(appointment!=null){
            appointment.setCustomer(iCustomerService.findCustomerById(appointmentDTORequest.customer().id()));
            appointment.setHairdresser(iHairdresserService.findHairdresserById(appointmentDTORequest.hairdresser().id()));
            appointment.setLastModification(new Date());
            appointment.setStatus(status(appointmentDTORequest.status()));
            return appointmentDTOMapper.apply(appointmentRepository.save(appointment));
        }else{
            return null;
        }
    }

    @Override
    public AppointmentDTOResponse changeHairdresser(Long id, HairdresserDTOResponse hairdresserDTOResponse) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if(appointment!=null){
            Hairdresser hairdresser = iHairdresserService.findHairdresserById(hairdresserDTOResponse.id());
            if(hairdresser!=null){
                appointment.setHairdresser(hairdresser);
                appointment.setLastModification(new Date());
                LOGGER.info("APPOINTMENT: appointment's hairdresser changed successfully");
                return appointmentDTOMapper.apply(appointmentRepository.saveAndFlush(appointment));
            }else{
                LOGGER.error("APPOINTMENT: the hairdresser doesn't exist, therefore is not possible change it");
                return null;
            }
        }else{
            LOGGER.error("APPOINTMENT: the appointment doesn't exist, therefore is not possible change its hairdresser");
            return null;
        }
    }

    @Override
    public AppointmentDTOResponse changeCustomer(Long id, CustomerDTOResponse customerDTOResponse) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if(appointment!=null){
            Customer customer = iCustomerService.findCustomerById(customerDTOResponse.id());
            if(customer!=null){
                appointment.setCustomer(customer);
                appointment.setLastModification(new Date());
                LOGGER.info("APPOINTMENT: appointment's customer changed successfully");
                return appointmentDTOMapper.apply(appointmentRepository.saveAndFlush(appointment));
            }else{
                LOGGER.error("APPOINTMENT: the customer doesn't exist, therefore is not possible change it");
                return null;
            }
        }else{
            LOGGER.error("APPOINTMENT: the appointment doesn't exist, therefore is not possible change its customer");
            return null;
        }
    }

    @Override
    public AppointmentDTOResponse addPayment(Long id, PaymentDTOResponse paymentDTOResponse) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if(appointment!=null){
            Payment payment = iPaymentService.findPaymentById(paymentDTOResponse.id());
            if(payment!=null){
                appointment.setPayment(payment);
                appointment.setLastModification(new Date());
                return appointmentDTOMapper.apply(appointmentRepository.saveAndFlush(appointment));
            }else{
                LOGGER.error("APPOINTMENT: the payment doesn't exist, therefore is not possible add it to the appointment");
                return null;
            }
        }else{
        LOGGER.error("APPOINTMENT: appointment doesn't exist, therefore is not possible add a payment");
        return null;
        }
    }

    @Override
    public AppointmentDTOResponse findById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if(appointment!=null){
            LOGGER.info("APPOINTMENT: appointment found successfully");
            return appointmentDTOMapper.apply(appointment);
        }else{
            LOGGER.error("APPOINTMENT: the appointment doesn't exist");
            return null;
        }
    }

    @Override
    public Set<AppointmentDTOResponse> findAppointmentsByStatus(String status) {
        return appointmentRepository.findAppointmentsByStatus(status).stream().map(appointment -> {
            return appointmentDTOMapper.apply(appointment);
        }).collect(Collectors.toSet());
    }

    @Override
    public Appointment findAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        return appointment!=null ? appointment : null;
    }

    @Override
    public Set<AppointmentDTOResponse> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        if(appointments!=null){
            LOGGER.info("CUSTOMER: customers found successfully");
            return appointments.stream().map(appointment -> {
                return appointmentDTOMapper.apply(appointment);
            }).collect(Collectors.toSet());
        }else {
            LOGGER.error("CUSTOMER: there aren't customers");
            return null;
        }
    }

    @Override
    public boolean remove(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if(appointment!=null){
            appointmentRepository.delete(appointment);
            LOGGER.info("APPOINTMENT: appointment deleted successfully");
            return true;
        }else{
            LOGGER.error("APPOINTMENT: the appointment doesn't exist,therefore is not possible remove it");
            return false;
        }
    }
}
