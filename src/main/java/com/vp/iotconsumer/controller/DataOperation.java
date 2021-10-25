/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.controller;

import com.vp.iotconsumer.converter.OptionalStringToOptionalLong;
import com.vp.iotconsumer.services.Services;

import com.vp.iotconsumer.util.process.IoTRequestDTO;
import com.vp.iotconsumer.util.process.IoTResponseDTO;
import java.io.FileNotFoundException;
import java.util.Optional;
import javax.management.AttributeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Bimal
 */
@RestController
@RequestMapping("/iot")
public class DataOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataOperation.class);

    @Autowired
    private Services service;

    @PostMapping(path = "/event/v1/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IoTResponseDTO> loadData(@RequestBody IoTRequestDTO ioTRequest) throws FileNotFoundException {
        LOGGER.info("+++ Calling loadData +++");

        return new ResponseEntity<>(service.loadDate(ioTRequest), HttpStatus.OK);
    }

    @GetMapping(path = "/event/v1", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<IoTResponseDTO> reportDevice(@RequestParam("ProductId") String productId,
            @RequestParam Optional<String> tstmp) throws AttributeNotFoundException {
        LOGGER.info("+++ Calling reportDevice +++");
        return new ResponseEntity<>(service.reportDevice(productId, OptionalStringToOptionalLong.convert(tstmp)), HttpStatus.OK);
    }

}
