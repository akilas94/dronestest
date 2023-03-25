package com.musalasoft.dronestest.service;

import com.musalasoft.dronestest.dto.MedicationDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.exception.ValidationException;

public interface MedicationService {

     ResponseDto saveMedicine(MedicationDto medicationDto) throws ValidationException;
}
