package com.musalasoft.dronestest.service;

import com.musalasoft.dronestest.dto.OrdersDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.entity.Orders;
import com.musalasoft.dronestest.exception.ValidationException;

public interface OrdersService {

    ResponseDto saveOrder(OrdersDto ordersDto) throws ValidationException;

}
