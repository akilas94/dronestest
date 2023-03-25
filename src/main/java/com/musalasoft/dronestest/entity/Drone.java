package com.musalasoft.dronestest.entity;

import com.musalasoft.dronestest.enums.Model;
import com.musalasoft.dronestest.enums.State;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private Model model;
    private double weight;
    private double batterCapacity;
    @Enumerated(EnumType.STRING)
    private State state;

}
