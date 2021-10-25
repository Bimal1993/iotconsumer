/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vp.iotconsumer.converter;

import java.util.Optional;
import java.util.OptionalLong;

/**
 *
 * @author Bimal
 */
public class OptionalStringToOptionalLong {

    public static OptionalLong convert(Optional<String> input) {
        OptionalLong inputLog = input.map(s -> OptionalLong.of(Long.parseLong(s))).orElseGet(OptionalLong::empty);
        return inputLog;
    }
}
