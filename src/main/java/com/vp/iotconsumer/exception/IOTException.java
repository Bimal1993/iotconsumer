/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.exception;

import com.vp.iotconsumer.info.InfoMessages;
import com.vp.iotconsumer.util.process.IoTResponseDTO;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import javax.management.AttributeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Bimal
 */
@ControllerAdvice
@RestController
public class IOTException extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOTException.class);

    @ExceptionHandler(AttributeNotFoundException.class)
    public final ResponseEntity<IoTResponseDTO> handleNotFoundException(AttributeNotFoundException exception) {
        LOGGER.error("AttributeNotFoundException, Message: {}", exception.getMessage());
        return new ResponseEntity<>(IoTResponseDTO.builder().description(InfoMessages.ERROR_GPS_NOT_REPORTED).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public final ResponseEntity<IoTResponseDTO> handleNotFoundException(FileNotFoundException exception) {
        LOGGER.error("FileNotFoundException, Message: {}", exception.getMessage());
        return new ResponseEntity<>(IoTResponseDTO.builder().description(InfoMessages.ERROR_NO_DATA_FOUND).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<IoTResponseDTO> handleNotFoundException(Exception exception) {
        LOGGER.error("Exception, Message: {}", exception.getMessage());
        return new ResponseEntity<>(IoTResponseDTO.builder().description(InfoMessages.ERROR_TECHNICAL_EXCEPTION).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<IoTResponseDTO> handleNotFoundException(NoSuchElementException exception) {
        LOGGER.error("NoSuchElementException, Message: {}", exception.getMessage());
        return new ResponseEntity<>(IoTResponseDTO.builder().description(InfoMessages.ERROR_ID_NOT_FOUND).build(), HttpStatus.NOT_FOUND);
    }

}
