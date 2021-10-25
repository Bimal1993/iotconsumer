/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.services;

import org.springframework.stereotype.Service;
import com.vp.iotconsumer.enums.ProductName;
import com.vp.iotconsumer.enums.Status;
import com.vp.iotconsumer.info.InfoMessages;
import com.vp.iotconsumer.model.IoT;
import com.vp.iotconsumer.repo.Diractory;
import com.vp.iotconsumer.util.process.CsvProcess;
import com.vp.iotconsumer.util.process.IoTRequestDTO;
import com.vp.iotconsumer.util.process.IoTResponseDTO;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalLong;
import java.util.stream.Collectors;
import javax.management.AttributeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Bimal
 */
@Service
public class Services {

    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

    @Autowired
    Diractory dataDiractory;

    public IoTResponseDTO loadDate(IoTRequestDTO iotrequest) {
        try {
            LOGGER.info(" Service entry ");
            dataDiractory.saveData(CsvProcess.processTrackingDevicesFile(iotrequest.getFilepath()));
            LOGGER.info("+++ IoT list updated +++");

            return IoTResponseDTO.builder().description(InfoMessages.HTTP_200_DATA_REFRESH).build();
        } catch (Exception e) {
            LOGGER.info("Error at: ", e);
        }
        return IoTResponseDTO.builder().description(InfoMessages.HTTP_200_DATA_REFRESH).build();
    }

    public IoTResponseDTO reportDevice(String productId, OptionalLong tstmp) throws AttributeNotFoundException {
        LOGGER.info("+++ Service entry +++");
        long timeToFilter = tstmp.orElse(Instant.now().toEpochMilli());

        List<IoT> iotList = dataDiractory.retrieveProductsToDate(productId, timeToFilter);
        IoT iotFound = findIotByDateTime(iotList, timeToFilter);

        checkIfDeviceIsAvailable(iotFound);

        String status = null;
        if (iotFound.getTrackerName().equals(ProductName.CYCLE_PLUS_TRACKER.toString())) {
            LOGGER.info("+++ Device {} detected +++", ProductName.CYCLE_PLUS_TRACKER);
            status = getStatusForCyclePlusTrackerDevices(iotList);
        }
        return IoTResponseDTO.from(iotFound, status);
    }

    private IoT findIotByDateTime(List<IoT> iotList, long timeToFilter) throws NoSuchElementException {
        LOGGER.info("+++ Filtering IoT list by DateTime +++");

        return iotList.stream().min(Comparator.comparingLong(iot -> timeToFilter - iot.getDateTime()))
                .orElseThrow(NoSuchElementException::new);
    }

    private void checkIfDeviceIsAvailable(IoT iotFound) throws AttributeNotFoundException {
        if (iotFound.getAirplaneMode().equals(Boolean.FALSE)
                && !iotFound.isGpsDataAvailable()) {
            LOGGER.error("+++ AirplaneMode Disabled and GPS data Not Available +++");
            throw new AttributeNotFoundException();
        }
    }

    /**
     * (This functionality is only for CyclePlusTracker devices)
     * <p>
     * Given a IoT list returns the device status based on the GPS data
     * retrieved.
     *
     * @param iotList of CyclePlusTracker devices
     * @return "N/A" if there are less than 3 records, if there are, returns
     * "Inactive" if the GPS data remains constant between records, "Active" if
     * they do not match
     */
    private String getStatusForCyclePlusTrackerDevices(List<IoT> iotList) {
        LOGGER.info("+++ Get status for CyclePlusTracker device +++");

        List<IoT> iotListFiltered = iotList.stream().sorted(Comparator.comparingLong(IoT::getDateTime).reversed())
                .limit(3).collect(Collectors.toList());

        if (iotListFiltered.size() >= 1 && iotListFiltered.stream().allMatch(IoT::isGpsDataAvailable)) {
            LOGGER.info("+++ 1 inputs with GPS data available +++");
            return areAllCoordinatesSame(iotListFiltered) ? Status.IN_ACTIVE.toString()
                    : Status.STATUS_NA.toString();
        } else {
            return InfoMessages.ERROR_ID_NOT_FOUND.toString();
        }

    }

    private boolean areAllCoordinatesSame(List<IoT> iotList) {
        return iotList.stream().allMatch(iot -> Objects.equals(iot.getLatitude(), iotList.get(0).getLatitude())
                && Objects.equals(iot.getLongitude(), iotList.get(0).getLongitude()));
    }
}
