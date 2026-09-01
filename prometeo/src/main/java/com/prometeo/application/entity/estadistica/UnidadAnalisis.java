package com.prometeo.application.entity.estadistica;

import java.util.*;

public abstract class UnidadAnalisis<I> {
    private final I id;
    private final Map<String, Variable<?>> attributes = new HashMap<>();

    public UnidadAnalisis(I id) {
        this.id = id;
    }

    protected void addVariable(Variable<?> variables) {
         attributes.put(variables.getName(), variables);
    }

    public Optional<Variable<?>> getVariable(String variableName) {
        return Optional.of(attributes.get(variableName));
    }

    public String getClassName() {
        return getClass().getName();
    }

    public Set<String> getAttributeNames() {
        return attributes.keySet();
    }

    public I getId() {
        return id;
    }

    protected abstract void extractAttributes();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UnidadAnalisis<?> that = (UnidadAnalisis<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
