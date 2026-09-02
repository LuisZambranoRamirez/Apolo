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

    public Optional<Variable.SubTypeVariable> getSubtypeVariabe(String attribute) {
        Variable<?> variable = attributes.get(attribute);
        if (variable == null) {
            return Optional.empty();
        }

        return Optional.of(variable.getSubTypeVariable());
    }

    public boolean hasSameStructure(UnidadAnalisis<?> other) {
        if (other == null) return false;
        // 1. Misma clase concreta de UnidadAnalisis
        if (!getClass().equals(other.getClass())) return false;

        // 2. Mismos nombres de variables
        if (!attributes.keySet().equals(other.attributes.keySet())) return false;


        // 3. Mismo subtipo de variable
        for (String name : attributes.keySet()) {
            Variable<?> variable1 = attributes.get(name);
            Variable<?> variable2 = other.attributes.get(name);

            if (variable1.getSubTypeVariable() != variable2.getSubTypeVariable()) {
                return false;
            }
        }

        return true;
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