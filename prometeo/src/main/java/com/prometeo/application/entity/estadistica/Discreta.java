package com.prometeo.application.entity.estadistica;

/**
 *
 * @author L
 */
public non-sealed class Discreta extends Variable<Integer>{
    public Discreta(String nombre, Integer valor) {
        super(nombre, SubtipoVariable.DISCRETA, valor);
    }
}
