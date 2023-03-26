package com.musalasoft.dronestest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MedicationDto {
    private Long id;
    private String name;
    private String code;
    private double weight;
    @JsonIgnore
    private MultipartFile file;
}
