/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.enums;

/**
 *
 * @author Bimal
 */
public enum ProductName {
    CYCLE_PLUS_TRACKER("CyclePlusTracker"), GENERAL_TRACKER("GeneralTracker"), UNKNOWN_TRACKER("Unknown");

    private final String value;

    private ProductName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
