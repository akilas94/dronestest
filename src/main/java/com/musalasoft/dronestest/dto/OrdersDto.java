package com.musalasoft.dronestest.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrdersDto {
    private Long droneId;
    private List<Long> medicationList;
}
