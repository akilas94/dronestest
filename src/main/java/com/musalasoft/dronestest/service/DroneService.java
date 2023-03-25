package com.musalasoft.dronestest.service;

import com.musalasoft.dronestest.dto.DroneDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.exception.ValidationException;

public interface DroneService {

    public ResponseDto saveDrone(DroneDto droneDto) throws ValidationException;
}
