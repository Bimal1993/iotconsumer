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
public enum Status {
    ACTIVE("active"), IN_ACTIVE("Inactive"),STATUS_NA("N/A");

	private final String status;

	private Status(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return status;
	}
}
