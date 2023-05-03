package com.barberback.service;

import com.barberback.model.dto.PaymentDTORequest;
import com.barberback.model.dto.PaymentDTOResponse;

import java.util.Set;

public interface IPaymentService {

    public PaymentDTOResponse save(PaymentDTORequest paymentDTORequest);
    public PaymentDTOResponse update(Long id, PaymentDTORequest paymentDTORequest);
    public PaymentDTOResponse updateStatus(Long id, String status);
    public PaymentDTOResponse updateMethod(Long id, String method);
    public PaymentDTOResponse findById(Long id);
    public Set<PaymentDTOResponse> findAll();
    public boolean remove(Long id);
}
