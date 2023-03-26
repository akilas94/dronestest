package com.musalasoft.dronestest.service.impl;

import com.musalasoft.dronestest.constants.AppConstants;
import com.musalasoft.dronestest.dto.MedicationDto;
import com.musalasoft.dronestest.dto.OrderDetailsDto;
import com.musalasoft.dronestest.dto.OrderRequestDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.entity.Drone;
import com.musalasoft.dronestest.entity.Medication;
import com.musalasoft.dronestest.entity.Orders;
import com.musalasoft.dronestest.enums.State;
import com.musalasoft.dronestest.exception.ValidationException;
import com.musalasoft.dronestest.repository.DroneRepository;
import com.musalasoft.dronestest.repository.MedicationRepository;
import com.musalasoft.dronestest.repository.OrdersRepository;
import com.musalasoft.dronestest.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static com.musalasoft.dronestest.constants.ErrorMessages.*;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Value("${drone.delivery.batterylevel}")
    private double deliveryBatteryLevel;

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public ResponseDto saveOrder(OrderRequestDto orderRequestDto) throws ValidationException {
        Optional<Drone> optionalDrone = droneRepository.findById(orderRequestDto.getDroneId());
        ResponseDto responseDto = new ResponseDto();
        Drone drone = new Drone();
        Orders orders = new Orders();
        if (optionalDrone.isPresent()) {
            drone = optionalDrone.get();
            validateDrone(drone);
            drone.setState(State.LOADING);
            drone = droneRepository.save(drone);
            orders.setDrone(drone);
        } else {
            throw new ValidationException(DRONES_EXIST);
        }
        Double weightOfAllItems = getWeightOfAllItems(orderRequestDto.getMedicationList());
        if (weightOfAllItems > orders.getDrone().getWeight()) {
            throw new ValidationException(MUST_BE_UNDER_WEIGHT);
        }
        List<Medication> medicationList = medicationRepository.findAllById(orderRequestDto.getMedicationList());
        if (medicationList.size() > 0) {
            orders.setMedicationList(medicationList);
        } else {
            throw new ValidationException(PLACE_ORDER_ERROR);
        }
        orders.setOrderDateTime(new Date());

        Orders ordersSave = ordersRepository.save(orders);
        if (Objects.nonNull(ordersSave)) {
            responseDto = new ResponseDto();
            responseDto.setStatus(AppConstants.SUCCESS);
            responseDto.setDescription(AppConstants.RECORD_SAVED);
            drone.setState(State.LOADED);
            droneRepository.save(drone);
        }
        return responseDto;

    }

    @Override
    public List<OrderDetailsDto> findOrderDetailsByDroneId(Long droneId) {
        List<OrderDetailsDto> detailsDtos = new ArrayList();
        ordersRepository.findAllByDrone_IdAndDrone_State(droneId, State.LOADED).forEach(order -> {
            OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
            orderDetailsDto.setOrderId(order.getId());
            orderDetailsDto.setMedicationList(getMedicineList(order.getMedicationList()));
            detailsDtos.add(orderDetailsDto);

        });
        return detailsDtos;
    }

    private List<MedicationDto> getMedicineList(List<Medication> list) {
        List<MedicationDto> medicationDtos = new ArrayList();
        list.forEach(medication -> {
            MedicationDto medicationDto = new MedicationDto();
            BeanUtils.copyProperties(medication, medicationDto);
            medicationDtos.add(medicationDto);

        });
        return medicationDtos;
    }

    private void validateDrone(Drone drone) throws ValidationException {

        if(!drone.getState().equals(State.IDLE)){
            throw new ValidationException(DRONE_ISNOT_AVAILABLE);
        }

        if (drone.getBatterCapacity() < deliveryBatteryLevel) {
            throw new ValidationException(BATTERY_ISNOT_ENOUGH);
        }
    }

    private double getWeightOfAllItems(List<Long> list){
        double [] weight = new double[1];
        list.forEach(id -> {
            Optional<Medication> medication = medicationRepository.findById(id);
            if(medication.isPresent()){
                weight[0]=weight[0]+medication.get().getWeight();
            }

        });
        return weight[0];
    }


}
