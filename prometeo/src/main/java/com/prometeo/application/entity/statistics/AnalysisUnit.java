package com.prometeo.application.entity.statistics;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class AnalysisUnit<I> {

    private final I id;

    private final Set<String> variableNames = new HashSet<>();

    private final Set<Nominal> nominalVariables = new HashSet<>();
    private final Set<Ordinal> ordinalVariables = new HashSet<>();
    private final Set<Continuous> continuousVariables = new HashSet<>();
    private final Set<Discrete> discreteVariables = new HashSet<>();

    public AnalysisUnit(I id) {
        this.id = id;
    }

    protected void addVariable(Nominal variable) throws Exception {
        if (variableNames.contains(variable.getName())) {
            throw new Exception("A variable with this name already exists.");
        }

        variableNames.add(variable.getName());
        nominalVariables.add(variable);
    }

    protected void addVariable(Ordinal variable) throws Exception {
        if (variableNames.contains(variable.getName())) {
            throw new Exception("A variable with this name already exists.");
        }

        variableNames.add(variable.getName());
        ordinalVariables.add(variable);
    }

    protected void addVariable(Continuous variable) throws Exception {
        if (variableNames.contains(variable.getName())) {
            throw new Exception("A variable with this name already exists.");
        }

        variableNames.add(variable.getName());
        continuousVariables.add(variable);
    }

    protected void addVariable(Discrete variable) throws Exception {
        if (variableNames.contains(variable.getName())) {
            throw new Exception("A variable with this name already exists.");
        }

        variableNames.add(variable.getName());
        discreteVariables.add(variable);
    }

    public Set<Nominal> getNominalVariables() {
        return new HashSet<>(nominalVariables);
    }

    public Set<Ordinal> getOrdinalVariables() {
        return new HashSet<>(ordinalVariables);
    }

    public Set<Continuous> getContinuousVariables() {
        return new HashSet<>(continuousVariables);
    }

    public Set<Discrete> getDiscreteVariables() {
        return new HashSet<>(discreteVariables);
    }

    public boolean hasSameStructure(AnalysisUnit<?> other) {
        if (other == null) {
            return false;
        }

        // Same concrete AnalysisUnit class
        if (!getClass().equals(other.getClass())) {
            return false;
        }

        // Same variables and variable types
        return nominalVariables.equals(other.nominalVariables)
                && ordinalVariables.equals(other.ordinalVariables)
                && continuousVariables.equals(other.continuousVariables)
                && discreteVariables.equals(other.discreteVariables);
    }

    public Set<String> getVariableNames() {
        return new HashSet<>(variableNames);
    }

    public I getId() {
        return id;
    }

    protected abstract void extractVariables();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AnalysisUnit<?> that = (AnalysisUnit<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}