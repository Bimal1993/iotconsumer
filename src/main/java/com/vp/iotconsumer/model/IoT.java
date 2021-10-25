/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByName;
import com.vp.iotconsumer.converter.csv.IntegerConverter;
import com.vp.iotconsumer.converter.csv.OffOnOperation;
import com.vp.iotconsumer.enums.BattaryLife;
import com.vp.iotconsumer.enums.ProductId;
import com.vp.iotconsumer.enums.ProductName;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author Bimal
 */
@Data
public class IoT {

    @CsvBindByName(column = "DateTime", required = true)
    @CsvBindByPosition(position = 0)
    private long dateTime;

    @CsvBindByName(column = "EventId", required = true)
    @CsvBindByPosition(position = 1)
    private long eventId;

    @CsvBindByName(column = "ProductId", required = true)
    @CsvBindByPosition(position = 2)
    private String productId;

    @CsvBindByName(column = "Latitude")
    // @CsvNumber(COLON)
    @CsvBindByPosition(position = 3)
    private BigDecimal latitude;

    @CsvBindByName(column = "Longitude")
    // @CsvNumber(COLON)
    @CsvBindByPosition(position = 4)
    private BigDecimal longitude;

    @CsvBindByPosition(position = 5)
    @CsvCustomBindByName(column = "Battery", required = true, converter = IntegerConverter.class)
    private Integer battery;

    @CsvBindByPosition(position = 6)
    @CsvCustomBindByName(column = "Light", required = true, converter = OffOnOperation.class)
    // private Optional<Boolean> light;
    private Object light;

    @CsvBindByPosition(position = 7)
    @CsvCustomBindByName(column = "AirplaneMode", required = true, converter = OffOnOperation.class)
    private Object airplaneMode;

    public boolean isGpsDataAvailable() {
        return this.latitude != null && this.longitude != null;
    }

    public String getBatteryLife() {
        String status = BattaryLife.BATTERY_LIFE_TODEAD.toString();

        if (this.battery >= 98) {
            status = BattaryLife.BATTERY_LIFE_HIGH.toString();
        } else if (this.battery >= 60) {
            status = BattaryLife.BATTERY_LIFE_MEDIUM.toString();
        } else if (this.battery >= 40) {
            status = BattaryLife.BATTERY_LIFE_LOW.toString();
        } else if (this.battery >= 10) {
            status = BattaryLife.BATTERY_LIFE_TODEAD.toString();
        }

        return status;
    }

    public String getTrackerName() {
        String productName = ProductName.UNKNOWN_TRACKER.toString();

        if (this.productId.startsWith(ProductId.STARTS_WITH_ID.toString())) {
            productName = ProductName.CYCLE_PLUS_TRACKER.toString();
        } else if (this.productId.startsWith(ProductId.STARTS_WITH_ID.toString())) {
            productName = ProductName.CYCLE_PLUS_TRACKER.toString();
        }

        return productName;
    }
}
