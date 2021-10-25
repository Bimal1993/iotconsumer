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
public enum BattaryLife {
       
	    BATTERY_LIFE_HIGH("High"),
	    BATTERY_LIFE_MEDIUM("Medium"),
	    BATTERY_LIFE_LOW("Low"),
	    BATTERY_LIFE_TODEAD("Critical");
	 
	  private final String value;

	   BattaryLife(String value) {
	        this.value = value;
	    }

	    @Override
	    public String toString() {
	        return value;
	    }
}
