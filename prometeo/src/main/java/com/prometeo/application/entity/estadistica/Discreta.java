package com.prometeo.application.entity.estadistica;

/**
 *
 * @author L
 */
public non-sealed class Discreta extends Variable<Double>{
    public Discreta(String nombre, Double valor) {
        super(nombre, SubTypeVariable.DISCRETA, valor);
    }
}