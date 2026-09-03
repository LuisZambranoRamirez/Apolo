package com.prometeo.application.entity.statistics;

/**
 *
 * @author L
 */
public non-sealed class Ordinal extends Variable<String> {
    public Ordinal(String name, String value) {
        super(name, VariableSubtype.ORDINAL, value);
    }
}
