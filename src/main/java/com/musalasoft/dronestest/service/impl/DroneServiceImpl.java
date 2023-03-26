package com.musalasoft.dronestest.service.impl;

import com.musalasoft.dronestest.constants.AppConstants;
import com.musalasoft.dronestest.dto.DroneDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.entity.Drone;
import com.musalasoft.dronestest.exception.ValidationException;
import com.musalasoft.dronestest.repository.DroneRepository;
import com.musalasoft.dronestest.service.DroneService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

import static com.musalasoft.dronestest.constants.ErrorMessages.*;

@Service
@Transactional
public class DroneServiceImpl implements DroneService {

    @Value("${drone.delivery.weight}")
    private double deliveryWeight;

    @Autowired
    private DroneRepository droneRepository;

    @Override
    public ResponseDto saveDrone(DroneDto droneDto) throws ValidationException {
        validateRequest(droneDto);
        ResponseDto responseDto = null;
        Drone drone = new Drone();
        BeanUtils.copyProperties(droneDto, drone);
        Drone droneSave = droneRepository.save(drone);
        if (Objects.nonNull(droneSave)) {
            responseDto = new ResponseDto();
            responseDto.setStatus(AppConstants.SUCCESS);
            responseDto.setDescription(AppConstants.RECORD_SAVED);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateBattery(DroneDto droneDto) {
        ResponseDto responseDto = new ResponseDto();
        if (droneRepository.updateBatteryByDroneId(droneDto.getId(), droneDto.getBatterCapacity()) > 0) {
            responseDto.setStatus(AppConstants.SUCCESS);
            responseDto.setDescription(AppConstants.RECORD_UPDATED);

        }
        return responseDto;
    }

    @Override
    public ResponseDto updateState(DroneDto droneDto) {
        ResponseDto responseDto = new ResponseDto();
        if (droneRepository.updateStatusByDroneId(droneDto.getId(), droneDto.getState().toString()) > 0) {
            responseDto.setStatus(AppConstants.SUCCESS);
            responseDto.setDescription(AppConstants.RECORD_UPDATED);

        }
        return responseDto;
    }

    private void validateRequest(DroneDto droneDto) throws ValidationException {
        if (droneDto.getSerialNumber().isEmpty()) {
            throw new ValidationException(SERIAL_NUMBER_CANNOT_BE_EMPTY);
        }

        if (droneRepository.findBySerialNumber(droneDto.getSerialNumber()).isPresent()) {
            throw new ValidationException(SERIAL_NUMBER_CANNOT_DUPLICATE);
        }

        if (droneDto.getSerialNumber().length() > 100) {
            throw new ValidationException(SERIAL_NUMBER_LENGTH);
        }

        if (droneDto.getWeight() > deliveryWeight) {
            throw new ValidationException(DRONE_WEIGHT_LIMIT);
        }
    }
}
