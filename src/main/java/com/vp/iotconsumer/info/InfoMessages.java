/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.info;

/**
 *
 * @author Bimal
 */
public interface InfoMessages {
  	// success
	String HTTP_200_DATA_REFRESH = "data refreshed";
	String HTTP_200_AIRPLANE_MODE_ENABLED = "Location is not available";
	String HTTP_200_LOCATION_IDENTIFIED = "Location identified";

	// Failure
	String ERROR_NO_DATA_FOUND = "ERROR no data file found";
	String ERROR_TECHNICAL_EXCEPTION = "ERROR A Technical exception occured";
	String ERROR_GPS_NOT_REPORTED = "ERROR Device could not be located";
	String ERROR_ID_NOT_FOUND = "ERROR Id <Insert ProductId> not Found";  
}
