/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.converter.csv;

import com.opencsv.bean.AbstractBeanField;
import com.vp.iotconsumer.enums.SwitchModes;
import java.util.Optional;

/**
 *
 * @author Bimal
 */
public class OffOnOperation extends AbstractBeanField {

    @Override
    public Optional<Boolean> convert(String value){
        return (value.equalsIgnoreCase(SwitchModes.ON.toString())) ? Optional.of(Boolean.TRUE)
                : ((value.equalsIgnoreCase(SwitchModes.OFF.toString())) ? Optional.of(Boolean.FALSE) : Optional.empty());
    }

}
