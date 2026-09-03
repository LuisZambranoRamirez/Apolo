package com.prometeo.application.entity.statistics;

/**
 *
 * @author L
 */
public non-sealed class Continuous extends Variable<Double>{
    public Continuous(String nombre, Double valor) {
        super(nombre, SubTypeVariable.CONTINUA, valor);
    }
}
