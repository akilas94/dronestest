package com.musalasoft.dronestest.dto;

import com.musalasoft.dronestest.enums.Model;
import com.musalasoft.dronestest.enums.State;
import lombok.Data;

@Data
public class DroneDto {

    private Long id;
    private String serialNumber;
    private Model model;
    private double weight;
    private double batterCapacity;
    private State state;
}
