package com.example.ecommerce.api.exceptions;


import com.example.ecommerce.exceptions.InditexParametersNotValid;
import com.example.ecommerce.exceptions.InditexPriceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public final class GlobalContException {

    @ExceptionHandler(InditexPriceNotFound.class)
    public ResponseEntity<?> handleInditexPriceNotFound(InditexPriceNotFound exception,
                                                                               WebRequest request) {
        return new ResponseEntity(new InditexConxServiceError(request.getParameterMap(), "error"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InditexParametersNotValid.class)
    public ResponseEntity<?> handleInditexParametersNotValid(InditexParametersNotValid exception,
                                                                                   WebRequest request) {
        return new ResponseEntity(new InditexConxServiceError(request.getParameterMap(), "error"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception, WebRequest request) {
        log.error("Exception received, message: <{}>", exception.getMessage());
        return new ResponseEntity(new InditexConxServiceError(request.getParameterMap(), "error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}