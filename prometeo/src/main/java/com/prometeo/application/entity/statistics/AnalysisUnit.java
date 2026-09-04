package com.prometeo.application.entity.statistics;

import java.util.*;

public abstract class AnalysisUnit<I> {

    private final I id;

    private final Set<Named> variableNames = new HashSet<>();

    private final Set<Nominal> nominalVariables = new LinkedHashSet<>();
    private final Set<Ordinal> ordinalVariables = new LinkedHashSet<>();
    private final Set<Continuous> continuousVariables = new LinkedHashSet<>();
    private final Set<Discrete> discreteVariables = new LinkedHashSet<>();

    public AnalysisUnit(I id) {
        this.id = id;
    }

    protected void addVariable(Nominal variable) throws Exception {
        if (variableNames.contains(variable)) {
            throw new Exception("A variable with this name already exists.");
        }

        variableNames.add(variable);
        nominalVariables.add(variable);
    }

    protected void addVariable(Ordinal variable) throws Exception {
        if (variableNames.contains(variable)) {
            throw new Exception("A variable with this name already exists.");
        }

        variableNames.add(variable);
        ordinalVariables.add(variable);
    }

    protected void addVariable(Continuous variable) throws Exception {
        if (variableNames.contains(variable)) {
            throw new Exception("A variable with this name already exists.");
        }

        variableNames.add(variable);
        continuousVariables.add(variable);
    }

    protected void addVariable(Discrete variable) throws Exception {
        if (variableNames.contains(variable)) {
            throw new Exception("A variable with this name already exists.");
        }

        variableNames.add(variable);
        discreteVariables.add(variable);
    }

    public Set<Nominal> getNominalVariables() {
        return new LinkedHashSet<>(nominalVariables);
    }

    public Set<Ordinal> getOrdinalVariables() {
        return new LinkedHashSet<>(ordinalVariables);
    }

    public Set<Continuous> getContinuousVariables() {
        return new LinkedHashSet<>(continuousVariables);
    }

    public Set<Discrete> getDiscreteVariables() {
        return new LinkedHashSet<>(discreteVariables);
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

    public I getId() {
        return id;
    }

    public abstract void initializeVariables();

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