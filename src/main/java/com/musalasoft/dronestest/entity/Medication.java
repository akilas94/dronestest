package com.musalasoft.dronestest.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private double weight;
    private String imagePath;
    @ManyToMany(mappedBy = "medicationList")
    private List<Orders> orders;
}
