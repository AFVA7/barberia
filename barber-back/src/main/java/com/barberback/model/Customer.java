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
public class Customer extends Person{

    @OneToOne
    @JoinColumn(name = "user_id")
    private _User user;
    @OneToOne
    @JoinColumn(name = "record_id")
    private Record record;
}
