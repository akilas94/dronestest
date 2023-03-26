package com.musalasoft.dronestest.service.impl;

import com.musalasoft.dronestest.dto.DroneDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.entity.Drone;
import com.musalasoft.dronestest.enums.Model;
import com.musalasoft.dronestest.enums.State;
import com.musalasoft.dronestest.exception.ValidationException;
import com.musalasoft.dronestest.repository.DroneRepository;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DroneServiceImplTest {

    @InjectMocks
    private DroneServiceImpl droneService;

    @Mock
    private DroneRepository droneRepository;

    @Test
    public void shouldSaveTheDrone() throws ValidationException {
        Drone drone = new Drone();
        ReflectionTestUtils.setField(droneService, "deliveryWeight", 500);
        BeanUtils.copyProperties(getDroneDto(), drone);
        Mockito.when(droneRepository.save(ArgumentMatchers.any())).thenReturn(drone);
        ResponseDto responseDto = droneService.saveDrone(getDroneDto());
        assertNotNull(responseDto);
    }


    private DroneDto getDroneDto() {
        DroneDto droneDto = new DroneDto();
        droneDto.setState(State.LOADED);
        droneDto.setId(1L);
        droneDto.setSerialNumber("4455");
        droneDto.setBatterCapacity(45);
        droneDto.setWeight(300);
        droneDto.setModel(Model.Cruiserweight);
        return droneDto;
    }
}