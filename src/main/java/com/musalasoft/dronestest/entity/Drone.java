package com.musalasoft.dronestest.entity;

import com.musalasoft.dronestest.enums.Model;
import com.musalasoft.dronestest.enums.State;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "drone")
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

    public Drone() {
    }

    public Drone(String serialNumber, Model model, double weight, double batterCapacity, State state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.batterCapacity = batterCapacity;
        this.state = state;
    }
}
