package com.musalasoft.dronestest.controller;

import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ResponseDto> resourceNotFoundException(ValidationException ex, WebRequest request) {
        ResponseDto message = new ResponseDto();
        message.setStatus("ERROR");
        message.setDescription(ex.getMessage());

        return new ResponseEntity<ResponseDto>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
