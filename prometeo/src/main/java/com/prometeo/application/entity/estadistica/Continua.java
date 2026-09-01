package com.prometeo.application.entity.estadistica;

/**
 *
 * @author L
 */
public non-sealed class Continua extends Variable<Double>{
    public Continua(String nombre, Double valor) {
        super(nombre, SubTypeVariable.CONTINUA, valor);
    }
}
