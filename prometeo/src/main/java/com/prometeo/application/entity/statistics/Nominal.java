package com.prometeo.application.entity.statistics;

import java.util.Set;

/**
 *
 * @author L
 */
public non-sealed class Nominal extends Variable<Set<String>> {
    public Nominal(String name, String value) {
        super(name, VariableSubtype.NOMINAL, Set.of(value));
    }

    public Nominal(String name, Set<String> value) {
        super(name, VariableSubtype.NOMINAL, value);
    }
}