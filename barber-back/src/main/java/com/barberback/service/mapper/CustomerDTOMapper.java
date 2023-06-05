package com.barberback.service.mapper;

import com.barberback.model.Customer;
import com.barberback.model.dto.CustomerDTOResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class CustomerDTOMapper implements Function<Customer, CustomerDTOResponse> {
    @Override
    public CustomerDTOResponse apply(Customer customer) {
        return new CustomerDTOResponse(
                customer.getId(),
                customer.getName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getEmail()
        );
    }
}
