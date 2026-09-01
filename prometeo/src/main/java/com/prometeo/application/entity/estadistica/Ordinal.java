package com.prometeo.application.entity.estadistica;

/**
 *
 * @author L
 */
public non-sealed class Ordinal extends Variable<String> {
    public Ordinal(String nombre, String valor) {
        super(nombre, SubTypeVariable.ORDINAL, valor);
    }
}
