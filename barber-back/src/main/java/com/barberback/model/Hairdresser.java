package com.barberback.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Hairdresser extends Person{

    @OneToOne
    @JoinColumn(name = "user_id")
    private _User user;
    private int employeeCode;
    @OneToMany(mappedBy = "hairdresser")
    private Set<Appointment> appointments;
    public void addAppointment(Appointment a){
        this.appointments.add(a);
        System.out.println("tam of appointments: "+appointments.size());
        a.setHairdresser(this);
    }
}
