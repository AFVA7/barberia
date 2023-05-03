package com.barberback.service.mapper;

import com.barberback.model.Payment;
import com.barberback.model.dto.CustomerDTOResponse;
import com.barberback.model.dto.PaymentDTOResponse;
import com.barberback.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class PaymentDTOMapper implements Function<Payment, PaymentDTOResponse> {
    @Autowired
    private ICustomerService iCustomerService;

    @Override
    public PaymentDTOResponse apply(Payment payment) {
        return new PaymentDTOResponse(
                payment.getId(),
                customer(payment.getId()),
                payment.getPaymentMethod().toString(),
                payment.getPaymentStatus().toString(),
                payment.getDate()
        );
    }

    private CustomerDTOResponse customer(Long id){
        CustomerDTOResponse customerDTOResponse = iCustomerService.findById(id);
        return customerDTOResponse!=null ? customerDTOResponse : null;
    }

}
