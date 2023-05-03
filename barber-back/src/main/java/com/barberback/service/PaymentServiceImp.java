package com.barberback.service;

import com.barberback.model.Customer;
import com.barberback.model.Payment;
import com.barberback.model.PaymentMethod;
import com.barberback.model.PaymentStatus;
import com.barberback.model.dto.PaymentDTORequest;
import com.barberback.model.dto.PaymentDTOResponse;
import com.barberback.repository.PaymentRepository;
import com.barberback.service.mapper.PaymentDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImp implements IPaymentService{

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFactory.class);
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private PaymentDTOMapper paymentDTOMapper;

    @Override
    public PaymentDTOResponse save(PaymentDTORequest paymentDTORequest) {
        Payment payment = new Payment(
                null,
                customer(paymentDTORequest.customer().id()),
                paymentMethod(paymentDTORequest.paymentMethod()),
                paymentStatus(paymentDTORequest.paymentStatus()),
                paymentDTORequest.date()
        );
        LOGGER.info("PAYMENT: payment saved successfully");
        return paymentDTOMapper.apply(paymentRepository.save(payment));
    }
    private PaymentStatus paymentStatus(String status){
        switch (status){
            case "PAID":
                return PaymentStatus.PAID;
            case "UN_PAID":
                return PaymentStatus.UN_PAID;
            default:
                return null;
        }
    }
    private PaymentMethod paymentMethod(String method){
        switch (method){
            case "METALLIC":
                return PaymentMethod.METALLIC;
            case "CREDIT_CARD":
                return PaymentMethod.CREDIT_CARD;
            case "PAY_PAL":
                return PaymentMethod.PAY_PAL;
            default:
                return null;
        }
    }
    private Customer customer(Long id){
        Customer c = iCustomerService.findCustomerById(id);
        return c!=null ? c : null;
    }

    @Override
    public PaymentDTOResponse update(Long id, PaymentDTORequest paymentDTORequest) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if(payment!=null){
            Customer c = customer(paymentDTORequest.customer().id());
            if(c!=null){
                payment.setCustomer(c);
                payment.setPaymentMethod(paymentMethod(paymentDTORequest.paymentMethod()));
                payment.setPaymentStatus(paymentStatus(paymentDTORequest.paymentStatus()));
                payment.setDate(paymentDTORequest.date());
                LOGGER.info("PAYMENT: payment updated successfully");
                return paymentDTOMapper.apply(paymentRepository.save(payment));
            }else{
                LOGGER.error("PAYMENT: the customer doesn't exist, therefore is not possible update the payment");
                return null;
            }
        }else{
            LOGGER.error("PAYMENT: the payment doesn't exist");
            return null;
        }
    }

    @Override
    public PaymentDTOResponse updateStatus(Long id, String status) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if(payment!=null){
            payment.setPaymentStatus(paymentStatus(status));
            LOGGER.info("PAYMENT: payment's status updated successfully");
            return paymentDTOMapper.apply(paymentRepository.save(payment));
        }else{
            LOGGER.error("PAYMENT: the payment doesn't exist, therefore is not possible update the status");
            return null;
        }
    }

    @Override
    public PaymentDTOResponse updateMethod(Long id, String method) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if(payment!=null){
            payment.setPaymentMethod(paymentMethod(method));
            LOGGER.info("PAYMENT: payment's method updated successfully");
            return paymentDTOMapper.apply(paymentRepository.save(payment));
        }else{
            LOGGER.error("PAYMENT: the payment doesn't exist, therefore is not possible update the method");
            return null;
        }
    }

    @Override
    public PaymentDTOResponse findById(Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if(payment!=null){
            LOGGER.info("PAYMENT: payment found successfully");
            return paymentDTOMapper.apply(payment);
        }else{
            LOGGER.error("PAYMENT: the payment doesn't exist");
            return null;

        }
    }

    @Override
    public Set<PaymentDTOResponse> findAll() {
        Set<Payment> payments = paymentRepository.findAll().stream().collect(Collectors.toSet());
        if(payments!=null){
            LOGGER.info("PAYMENT: payments found successfully");
            return payments.stream().map(payment->{
                return paymentDTOMapper.apply(payment);
            }).collect(Collectors.toSet());
        }else{
            LOGGER.error("PAYMENT: there aren't payments");
            return null;
        }
    }

    @Override
    public boolean remove(Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if(payment!=null){
            paymentRepository.delete(payment);
            LOGGER.info("PAYMENT: payment deleted successfully");
            return true;
        }else{
            LOGGER.error("PAYMENT: the payment doesn't exist, therefore is not possible delete it");
            return false;
        }
    }
}
