/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.repo;

import com.vp.iotconsumer.model.IoT;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bimal
 */
@Repository
public class Diractory {

    private static final Logger LOGGER = LoggerFactory.getLogger(Diractory.class);

    private List<IoT> mockIoTList = new ArrayList<>();

    /**
     * Replaces the IoT list with the new data received
     *
     * @param iotList to store
     * @return IoT list stored
     */
    public List<IoT> saveData(List<IoT> iotList) {
        LOGGER.info("+++ Repository entry +++");
        
        mockIoTList = iotList;
        return mockIoTList;
    }

    /**
     * Given a ProductId and a DateTime, it searches for IoTs that match the
     * ProductId and have a DateTime equal to or earlier than the one provided.
     *
     * @param productId to look for
     * @param timeToFilter to look for
     * @return IoT list filtered
     */
    public List<IoT> retrieveProductsToDate(String productId, long timeToFilter) {
        LOGGER.info("+++ Repository entry +++");

        return mockIoTList.stream()
                .filter(iot -> iot.getProductId().equals(productId))
                .filter(iot -> iot.getDateTime() <= timeToFilter).collect(Collectors.toList());
    }
}
