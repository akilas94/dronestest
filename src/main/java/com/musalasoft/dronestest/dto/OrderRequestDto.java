package com.musalasoft.dronestest.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private Long droneId;
    private List<Long> medicationList;
}
