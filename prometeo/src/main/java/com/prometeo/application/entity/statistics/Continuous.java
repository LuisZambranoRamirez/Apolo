package com.prometeo.application.entity.statistics;

/**
 *
 * @author L
 */
public non-sealed class Continuous extends Variable<Double>{
    public Continuous(String name, Double value) {
        super(name, VariableSubtype.CONTINUOUS, value);
    }
}
