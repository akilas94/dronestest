package com.musalasoft.dronestest.controller;

import com.musalasoft.dronestest.dto.DroneDto;
import com.musalasoft.dronestest.dto.OrderDetailsDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.exception.ValidationException;
import com.musalasoft.dronestest.service.DroneService;
import com.musalasoft.dronestest.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drones")
@Slf4j
public class DroneController {
    @Autowired
    private DroneService droneService;

    @Autowired
    private OrdersService ordersService;

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody DroneDto droneDto) throws ValidationException {
        ResponseDto responseDto = null;
        responseDto = droneService.saveDrone(droneDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/odetails/{droneId}")
    public ResponseEntity<List<OrderDetailsDto>> save(@PathVariable("droneId") Long droneId) {
        List<OrderDetailsDto> orderDetailsDtos = ordersService.findOrderDetailsByDroneId(droneId);
        return new ResponseEntity<>(orderDetailsDtos, HttpStatus.OK);
    }

    @PatchMapping("/battery")
    public ResponseEntity<ResponseDto> updateBattery(@RequestBody DroneDto droneDto) {
        ResponseDto responseDto = droneService.updateBattery(droneDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/state")
    public ResponseEntity<ResponseDto> updateState(@RequestBody DroneDto droneDto) {
        ResponseDto responseDto = droneService.updateState(droneDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}


