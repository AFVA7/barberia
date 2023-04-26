package com.barberback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hairdresser extends Person{

    @OneToOne
    @JoinColumn(name = "user_id")
    private _User user;
    private int employeeCode;
    @OneToMany(mappedBy = "hairdresser")
    private Set<Appointment> appointments;
}
