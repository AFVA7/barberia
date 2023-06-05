package com.barberback.repository;

import com.barberback.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    //TODO: make a query to fetch a list of appointment by status and another to date
    @Query(value = "SELECT a FROM appointment WHERE a.status= ?1", nativeQuery = true)
    List<Appointment> findAppointmentsByStatus(String status);
}
