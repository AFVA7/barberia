package com.barberback.repository;

import com.barberback.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    //TODO: create queries to fetch by status and method

    Optional<Payment> findPaymentByPaymentStatus(String status);
    Optional<Payment> findPaymentByPaymentMethod(String method);
}
