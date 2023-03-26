package com.musalasoft.dronestest.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailsDto {
    private Long orderId;
    private List<MedicationDto> medicationList;
}
