package com.musalasoft.dronestest.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="drone_id")
    private Drone drone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDateTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_details",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id",
                    referencedColumnName = "id"))
    private List<Medication> medicationList;
}