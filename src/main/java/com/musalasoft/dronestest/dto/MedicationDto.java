package com.musalasoft.dronestest.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MedicationDto {
    private Long id;
    private String name;
    private String code;
    private double weight;
    private MultipartFile file;
}
