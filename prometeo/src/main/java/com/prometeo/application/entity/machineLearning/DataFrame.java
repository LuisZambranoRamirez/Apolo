package com.prometeo.application.entity.machineLearning;

import com.prometeo.application.entity.statistics.*;

import java.util.*;

public class DataFrame<I> {
    private final Set<AnalysisUnit<I>> analysisUnits;
    private final Map<Named, Set<String>> variableNominalValues = new HashMap<>();

    public DataFrame(Set<AnalysisUnit<I>> analysisUnits) {
        if (analysisUnits.isEmpty()) {
            throw new IllegalArgumentException("Analysis units cannot be empty");
        }

        AnalysisUnit<I> referenceUnit = analysisUnits.iterator().next();

        for (Nominal nominalVariable : referenceUnit.getNominalVariables()) {
            variableNominalValues.put(nominalVariable, new HashSet<>());
        }

        for (AnalysisUnit<I> analysisUnit : analysisUnits) {
            if (!referenceUnit.hasSameStructure(analysisUnit)) {
                throw new IllegalArgumentException(
                        "All analysis units must have the same structure. " +
                                "Expected: " + referenceUnit.getClass().getName()
                );
            }

            for (Nominal nominalVariable : analysisUnit.getNominalVariables()) {
                Set<String> values = variableNominalValues.get(nominalVariable);

                values.addAll(nominalVariable.getValue());
            }
        }

        this.analysisUnits = analysisUnits;
    }

    public Set<String> getNominalValues(Named variable) {
        Set<String> values = variableNominalValues.get(variable);
        return new HashSet<>(values);
    }


}