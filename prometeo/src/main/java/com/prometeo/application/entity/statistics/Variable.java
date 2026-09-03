package com.prometeo.application.entity.statistics;

import java.util.Objects;

/**
 *
 * @author L
 * @param <E>
 */
public abstract sealed class Variable<E> permits Ordinal, Continuous, Discrete, Nominal {
    private final String name;
    private final E value;
    private final TypeVariable typeVariable;
    private final SubTypeVariable subTypeVariable;

    public Variable(String name, SubTypeVariable subTypeVariable, E value) {
        this.name = name;
        this.value = value;
        this.subTypeVariable = subTypeVariable;

        this.typeVariable = switch (subTypeVariable) {
            case NOMINAL, ORDINAL -> TypeVariable.CUALITATIVA;
            case DISCRETA, CONTINUA -> TypeVariable.CUANTITATIVA;
        };
    }

    public String getName() {
        return name;
    }

    public E getValue() {
        return value;
    }

    public boolean isEscalar() {
        if (this instanceof Nominal nominal) {
            return nominal.getValue().size() == 1;
        }
        return true;
    }

    public TypeVariable getTypeVariable() {
        return typeVariable;
    }

    public SubTypeVariable getSubTypeVariable() {
        return subTypeVariable;
    }

    public enum SubTypeVariable {
        NOMINAL,
        ORDINAL,
        DISCRETA,
        CONTINUA
    }

    public enum TypeVariable {
        CUALITATIVA,
        CUANTITATIVA
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Variable<?> variable = (Variable<?>) o;
        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}