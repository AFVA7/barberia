package com.barberback.service.mapper;

import com.barberback.model.Appointment;
import com.barberback.model.Customer;
import com.barberback.model.Hairdresser;
import com.barberback.model.Payment;
import com.barberback.model.dto.AppointmentDTOResponse;
import com.barberback.model.dto.CustomerDTOResponse;
import com.barberback.model.dto.HairdresserDTOResponse;
import com.barberback.model.dto.PaymentDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class AppointmentDTOMapper implements Function<Appointment, AppointmentDTOResponse> {
    @Autowired
    private CustomerDTOMapper customerDTOMapper;
    @Autowired
    private HairdresserDTOMapper hairdresserDTOMapper;

    @Override
    public AppointmentDTOResponse apply(Appointment appointment) {
        return new AppointmentDTOResponse(
                appointment.getId(),
                customer(appointment.getCustomer()),
                hairdresser(appointment.getHairdresser()),
                appointment.getDate(),
                appointment.getStatus(),
                payment(appointment.getPayment())
        );
    }
    private CustomerDTOResponse customer(Customer customer){
        return customer!=null ? customerDTOMapper.apply(customer): null;
    }
    private HairdresserDTOResponse hairdresser(Hairdresser hairdresser){
        return hairdresser!=null ? hairdresserDTOMapper.apply(hairdresser) : null;
    }
    private PaymentDTOResponse payment(Payment payment){
        //TODO: finish this method
        return null;
    }
}
