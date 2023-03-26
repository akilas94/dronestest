package com.musalasoft.dronestest.service;

import com.musalasoft.dronestest.dto.OrderDetailsDto;
import com.musalasoft.dronestest.dto.OrderRequestDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.exception.ValidationException;

import java.util.List;

public interface OrdersService {

    ResponseDto saveOrder(OrderRequestDto orderRequestDto) throws ValidationException;

    List<OrderDetailsDto> findOrderDetailsByDroneId(Long droneId);

}
