package com.musalasoft.dronestest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musalasoft.dronestest.dto.DroneDto;
import com.musalasoft.dronestest.dto.MedicationDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.entity.Medication;
import com.musalasoft.dronestest.exception.ValidationException;
import com.musalasoft.dronestest.repository.MedicationRepository;
import com.musalasoft.dronestest.service.MedicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/medication")
@Slf4j
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestParam("file") MultipartFile file, String json)
            throws ValidationException, IOException {
        ResponseDto responseDto = null;
        MedicationDto medicationDto = getObjectFromJson(json);
        medicationDto.setFile(file);
        responseDto = medicationService.saveMedicine(medicationDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    private MedicationDto getObjectFromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, MedicationDto.class);
    }
}
