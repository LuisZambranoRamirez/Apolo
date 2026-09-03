package com.prometeo.application.entity.statistics;

/**
 *
 * @author L
 */
public non-sealed class Ordinal extends Variable<String> {
    public Ordinal(String nombre, String valor) {
        super(nombre, SubTypeVariable.ORDINAL, valor);
    }
}
