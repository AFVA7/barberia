package com.barberback.repository;

import com.barberback.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    //TODO: make a query to fetch a list of appointment by status and another to date
    Set<Appointment> findAppointmentsByStatus(String status);
}
