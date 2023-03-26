package com.musalasoft.dronestest.controller;

import com.musalasoft.dronestest.dto.OrderRequestDto;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.exception.ValidationException;
import com.musalasoft.dronestest.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody OrderRequestDto orderRequestDto)
            throws ValidationException {
        ResponseDto responseDto = null;
        responseDto = ordersService.saveOrder(orderRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }
}
