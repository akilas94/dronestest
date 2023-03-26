package com.musalasoft.dronestest.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "battery_hst")
public class BatteryHst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="drone_id")
    private Drone drone;

    private double batteryCapacity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDateTime;


}
