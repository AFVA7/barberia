package com.barberback.service;

import com.barberback.model.Customer;
import com.barberback.model.Record;
import com.barberback.model.dto.CustomerDTORequest;
import com.barberback.model.dto.CustomerDTOResponse;
import com.barberback.model.dto.RecordDTOResponse;

import java.util.Set;

public interface ICustomerService {
    public CustomerDTOResponse save(CustomerDTORequest customerDTORequest);
    public CustomerDTOResponse update(Long id, CustomerDTOResponse customerDTOResponse);
    public CustomerDTOResponse findById(Long id);
    public Customer findCustomerById(Long id);
    public Set<CustomerDTOResponse> findAll();
    public CustomerDTOResponse changeRecord(Long id, RecordDTOResponse recordDTOResponse);
    public boolean remove(Long id);


}
