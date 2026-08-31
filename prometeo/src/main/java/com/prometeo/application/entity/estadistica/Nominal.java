package com.prometeo.application.entity.estadistica;

/**
 *
 * @author L
 */
public non-sealed class Nominal extends Variable<String> {
    public Nominal(String nombre, String valor) {
        super(nombre, SubtipoVariable.NOMINAL, valor);
    }
}
