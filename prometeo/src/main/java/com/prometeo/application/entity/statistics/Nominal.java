package com.prometeo.application.entity.statistics;

import java.util.Set;

/**
 *
 * @author L
 */
public non-sealed class Nominal extends Variable<Set<String>> {
    public Nominal(String name, String valor) {
        super(name, SubTypeVariable.NOMINAL, Set.of(valor));
    }

    public Nominal(String name, Set<String> valor) {
        super(name, SubTypeVariable.NOMINAL, valor);
    }
}