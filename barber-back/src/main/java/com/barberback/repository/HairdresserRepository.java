package com.barberback.repository;

import com.barberback.model.Hairdresser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HairdresserRepository extends JpaRepository<Hairdresser,Long> {
    Optional<Hairdresser> findByEmployeeCode(int code);
}
