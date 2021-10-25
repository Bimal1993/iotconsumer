/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.converter.csv;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

/**
 *
 * @author Bimal
 */
public class IntegerConverter extends AbstractBeanField{

    @Override
    public Integer convert(String value)  {
       	return (int) (Double.parseDouble(value.replace(":", ".")) * 100); 
    }
    
}
