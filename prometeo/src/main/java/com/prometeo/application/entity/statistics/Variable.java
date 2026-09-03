package com.prometeo.application.entity.statistics;

import java.util.Objects;

/**
 * Represents a statistical variable.
 *
 * @param <E> the type of value stored by the variable
 */
public abstract sealed class Variable<E>
        implements Named
        permits Ordinal, Continuous, Discrete, Nominal {

    private final String name;
    private final E value;
    private final VariableType variableType;
    private final VariableSubtype variableSubtype;

    public Variable(String name, VariableSubtype variableSubtype, E value) {
        this.name = name;
        this.value = value;
        this.variableSubtype = variableSubtype;

        this.variableType = switch (variableSubtype) {
            case NOMINAL, ORDINAL -> VariableType.CATEGORICAL;
            case DISCRETE, CONTINUOUS -> VariableType.QUANTITATIVE;
        };
    }
    @Override
    public String getName() {
        return name;
    }

    public E getValue() {
        return value;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public VariableSubtype getVariableSubtype() {
        return variableSubtype;
    }

    public enum VariableSubtype {
        NOMINAL,
        ORDINAL,
        DISCRETE,
        CONTINUOUS
    }

    public enum VariableType {
        CATEGORICAL,
        QUANTITATIVE
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Variable<?> variable = (Variable<?>) o;
        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
