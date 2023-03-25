package com.musalasoft.dronestest.entity;

import lombok.Data;

import javax.persistence.*;

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
}
