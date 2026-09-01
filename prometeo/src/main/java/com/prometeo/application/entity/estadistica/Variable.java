package com.prometeo.application.entity.estadistica;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author L
 * @param <E>
 */
public abstract sealed class Variable<E> permits Ordinal, Continua, Discreta, Nominal {
    private final String name;
    private final List<E> value;
    private final TypeVariable typeVariable;
    private final SubTypeVariable subTypeVariable;

    public Variable(String name, SubTypeVariable subTypeVariable, List<E> value) {
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

    public List<E> getValue() {
        return List.copyOf(value);
    }

    public boolean isEscalar() {
        return value.size() == 1;
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
