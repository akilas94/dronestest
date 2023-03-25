package com.musalasoft.dronestest.service.impl;

import com.musalasoft.dronestest.dto.DroneDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.entity.Drone;
import com.musalasoft.dronestest.exception.ValidationException;
import com.musalasoft.dronestest.repository.DroneRepository;
import com.musalasoft.dronestest.service.DroneService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class DronServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Override
    public ResponseDto saveDrone(DroneDto droneDto) throws ValidationException{
        validateRequest(droneDto);
        ResponseDto responseDto = new ResponseDto();
        Drone drone = new Drone();
        BeanUtils.copyProperties(droneDto, drone);
        Drone droneSave = droneRepository.save(drone);
        if(Objects.nonNull(droneSave)){
            responseDto = new ResponseDto();
            responseDto.setStatus("SUCCESS");
            responseDto.setDescription("Record Save SuccessFuly");
        }
        return responseDto;
    }

    private void validateRequest(DroneDto droneDto) throws ValidationException{
        if(droneDto.getSerialNumber().isEmpty()){
            throw new ValidationException("Serial Number Cannot be empty");
        }
        if(droneDto.getSerialNumber().length() > 100 ){
            throw new ValidationException("Serial Number length Cannot be getter than 100");
        }
    }
}
