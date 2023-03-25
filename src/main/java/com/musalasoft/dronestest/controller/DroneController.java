package com.musalasoft.dronestest.controller;

import com.musalasoft.dronestest.dto.DroneDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.exception.ValidationException;
import com.musalasoft.dronestest.service.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/drones")
@Slf4j
public class DroneController {
    @Autowired
    private DroneService droneService;

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody DroneDto droneDto) throws ValidationException {
        ResponseDto responseDto = null;
        responseDto = droneService.saveDrone(droneDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }
}


