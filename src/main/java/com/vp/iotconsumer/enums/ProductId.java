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
public enum ProductId {
    
	STARTS_WITH_ID("WG");

	private final String value;

	private ProductId(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
