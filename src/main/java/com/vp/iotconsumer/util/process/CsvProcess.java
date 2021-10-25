/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.util.process;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vp.iotconsumer.model.IoT;
import java.util.ArrayList;

/**
 *
 * @author Bimal
 */
public class CsvProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvProcess.class);

    public static List<IoT> processTrackingDevicesFile(String filepath) throws FileNotFoundException {
        LOGGER.info("+++ Retrieving and mapping data from {} +++", filepath);
        List<IoT> iotLists = new ArrayList<>();
        try {
            // create csv bean reader
            CsvToBean csvToBean = new CsvToBeanBuilder(new FileReader(filepath))
                    .withType(IoT.class).withSeparator(',').withSkipLines(2)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            iotLists = csvToBean.parse();
            LOGGER.info("+++ Retrieving and mapping data from {} +++", iotLists);
        } catch (Exception e) {
            LOGGER.info("+++ Retrieving and mapping data from {} +++", e);
        }
        return iotLists;
    }

}
