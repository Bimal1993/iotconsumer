/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.util.process;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vp.iotconsumer.enums.Status;
import com.vp.iotconsumer.info.InfoMessages;
import com.vp.iotconsumer.model.IoT;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Bimal
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "datetime", "longitude", "lat", "status", "battery", "description"})
public class IoTResponseDTO {

    private static final Logger LOGGER = LoggerFactory.getLogger(IoTResponseDTO.class);

    private String id; // ProductId

    private String name;

    private String datetime;

    @JsonProperty("long")
    private String longitude;

    private String lat;

    private String status;

    private String battery;

    private String description;

    public static IoTResponseDTO from(IoT source, String status) {
        LOGGER.info("+++ Create IoTResponseDTO from IoT +++");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getDateTime()), ZoneId.systemDefault());

        IoTResponseDTOBuilder builder = IoTResponseDTO.builder()
                .id(source.getProductId())
                .battery(source.getBatteryLife())
                .name(source.getTrackerName())
                .datetime(dateTime.format(dtf));
        
        if (source.getAirplaneMode().equals("OFF")) { 
            LOGGER.info("+++ AirplaneMode as False +++");
            builder = builder 
                    .longitude(source.getLongitude().toString()) 
                    .lat(source.getLatitude().toString())
                    .status(Status.ACTIVE.toString())
                    .description(InfoMessages.HTTP_200_LOCATION_IDENTIFIED);
        } else {
            LOGGER.info("+++ AirplaneMode as True +++");
            builder = builder
                    .status(Status.IN_ACTIVE.toString())
                    .description(InfoMessages.HTTP_200_AIRPLANE_MODE_ENABLED);
        }

        // Override the status
        if (status != null) {
            builder = builder.status(status);
        }

        return builder.build();
    }

}
