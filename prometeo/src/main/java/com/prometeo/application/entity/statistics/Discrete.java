package com.prometeo.application.entity.statistics;

/**
 *
 * @author L
 */
public non-sealed class Discrete extends Variable<Double>{
    public Discrete(String nombre, Double valor) {
        super(nombre, SubTypeVariable.DISCRETA, valor);
    }
}