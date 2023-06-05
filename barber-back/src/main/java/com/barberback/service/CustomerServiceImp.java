package com.barberback.service;

import com.barberback.model.Admin;
import com.barberback.model.Customer;
import com.barberback.model.Record;
import com.barberback.model._User;
import com.barberback.model.dto.CustomerDTORequest;
import com.barberback.model.dto.CustomerDTOResponse;
import com.barberback.model.dto.RecordDTOResponse;
import com.barberback.repository.CustomerRepository;
import com.barberback.service.mapper.CustomerDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImp implements ICustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFactory.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IRecordService iRecordService;
    @Autowired
    private CustomerDTOMapper customerDTOMapper;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTOResponse save(CustomerDTORequest customerDTORequest) {
        _User user = null;
        Long id;
        if(customerDTORequest.UserDTORequest()!=null){
            id = userService.save(customerDTORequest.UserDTORequest()).id();
            user=userService.findUserById(id);
        }
        Customer customer = new Customer();
        customer.setName(customerDTORequest.name());
        customer.setLastName(customerDTORequest.lastName());
        customer.setPhone(customerDTORequest.phone());
        customer.setEmail(customerDTORequest.email());
        customer.setRecord(new Record(null,new HashSet<>()));
        customer.setUser(user);

        LOGGER.info("CUSTOMER: customer created successfully");
        return customerDTOMapper.apply(customerRepository.save(customer));
    }

    @Override
    public CustomerDTOResponse update(Long id, CustomerDTOResponse customerDTOResponse) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer!=null){
            customer.setName(customerDTOResponse.name());
            customer.setLastName(customerDTOResponse.lastName());
            customer.setPhone(customerDTOResponse.phone());
            customer.setEmail(customerDTOResponse.email());
            LOGGER.info("CUSTOMER: customer updated successfully");
            return customerDTOMapper.apply(customerRepository.save(customer));
        }else{
            LOGGER.error("CUSTOMER: customer doesn't exist, therefore is not possible update it");
            return null;
        }

    }

    @Override
    public CustomerDTOResponse findById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer!=null){
            LOGGER.info("CUSTOMER: customer found successfully");
            return customerDTOMapper.apply(customer);
        }else{
            LOGGER.error("CUSTOMER: customer doesn't exist");
            return null;
        }
    }

    @Override
    public Customer findCustomerById(Long id) {
        return  customerRepository.findById(id).orElse(null);
    }

    @Override
    public Set<CustomerDTOResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();
        if(customers!=null){
            LOGGER.info("CUSTOMER: customers found successfully");
            return customers.stream().map(customer -> {
                System.out.println("Customer's record: "+show(customer));
                return customerDTOMapper.apply(customer);
            }).collect(Collectors.toSet());
        }else {
            LOGGER.error("CUSTOMER: there aren't customers");
            return null;
        }
    }
    //TODO: delete this because is a test
    private String show(Customer customer){
        if(customer.getRecord()!=null){
            return customer.getRecord().getId()+"";
        }else{
            return "0";
        }
    }
    @Override
    public CustomerDTOResponse changeRecord(Long id, RecordDTOResponse recordDTOResponse) {
        Customer customer = customerRepository.findById(id).orElse(null);
        Record record = iRecordService.findRecordById(recordDTOResponse.id());
        if(customer!=null && record!=null){
            customer.setRecord(null);
            customer.setRecord(record);
            return customerDTOMapper.apply(customerRepository.saveAndFlush(customer));
        }else{
            return null;
        }
    }


    @Override
    public boolean remove(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer==null){
            LOGGER.error("CUSTOMER: the customer to remove doesn't exist");
            return false;
        }else{
            LOGGER.info("CUSTOMER: customer removed successfully");
            customerRepository.delete(customer);
            return true;
        }
    }
}
