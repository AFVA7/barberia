package com.barberback.service.mapper;

import com.barberback.model.Customer;
import com.barberback.model.dto.CustomerDTOResponse;

import java.util.function.Function;

public class CustomerDTOMapper implements Function<Customer, CustomerDTOResponse> {
    @Override
    public CustomerDTOResponse apply(Customer customer) {
        return new CustomerDTOResponse(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail()
        );
    }
}
