package com.musalasoft.dronestest.service.impl;

import com.musalasoft.dronestest.constants.AppConstants;
import com.musalasoft.dronestest.dto.OrdersDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.entity.Drone;
import com.musalasoft.dronestest.entity.Medication;
import com.musalasoft.dronestest.entity.Orders;
import com.musalasoft.dronestest.exception.ValidationException;
import com.musalasoft.dronestest.repository.DroneRepository;
import com.musalasoft.dronestest.repository.MedicationRepository;
import com.musalasoft.dronestest.repository.OrdersRepository;
import com.musalasoft.dronestest.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public ResponseDto saveOrder(OrdersDto ordersDto) throws ValidationException {
        Optional<Drone> optionalDrone = droneRepository.findById(ordersDto.getDroneId());
        ResponseDto responseDto = new ResponseDto();
        Orders orders = new Orders();
        if (optionalDrone.isPresent()) {
            orders.setDrone(optionalDrone.get());
        } else {
            throw new ValidationException("Drone doesn't exist");
        }
        Double weightOfAllItems = medicationRepository.findWeightOfAllItems(ordersDto.getMedicationList());
        if (weightOfAllItems <= orders.getDrone().getWeight()) {
            throw new ValidationException("Weight must be under 500g");
        }
        List<Medication> medicationList = medicationRepository.findAllById(ordersDto.getMedicationList());
        if (medicationList.size() > 0) {
            orders.setMedicationList(medicationList);
        } else {
            throw new ValidationException("At least need one medicine to make a order");
        }
        orders.setOrderDateTime(new Date());
        Orders ordersSave = ordersRepository.save(orders);
        if(Objects.nonNull(ordersSave)){
            responseDto = new ResponseDto();
            responseDto.setStatus(AppConstants.SUCCESS);
            responseDto.setDescription(AppConstants.RECORD_SAVED);
        }
        return responseDto;

    }

}
