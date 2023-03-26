package com.musalasoft.dronestest.controller;

import com.musalasoft.dronestest.constants.AppConstants;
import com.musalasoft.dronestest.dto.ResponseDto;
import com.musalasoft.dronestest.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@PropertySource({"classpath:error.properties"})
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Environment environment;

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ResponseDto> validationException(ValidationException ex, WebRequest request) {
        ResponseDto message = new ResponseDto();
        message.setStatus(AppConstants.ERROR);
        message.setDescription(getConfigValue(ex.getMessage()));

        return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getConfigValue(String key) {
        return this.environment.getProperty(key);
    }
}
