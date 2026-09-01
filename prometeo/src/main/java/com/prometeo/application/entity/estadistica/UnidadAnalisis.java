package com.prometeo.application.entity.estadistica;

import java.util.*;

public abstract class UnidadAnalisis<I> {
    private final I id;
    private final String subjectUnityAnalisis;
    private final Map<String, Variable<?>> attributes = new HashMap<>();

    public UnidadAnalisis(I id, String subjectUnityAnalisis) {
        this.id = id;
        this.subjectUnityAnalisis = subjectUnityAnalisis;
    }

    protected void addVariable(Variable<?> variables) {
         attributes.put(variables.getName(), variables);
    }

    public Variable<?> getVariable(String variableName) {
        return attributes.get(variableName);
    }

    public String getSubjectUnityAnalisis() {
        return subjectUnityAnalisis;
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
