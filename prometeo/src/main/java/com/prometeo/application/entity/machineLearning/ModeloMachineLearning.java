package com.prometeo.application.entity.machineLearning;

import com.prometeo.application.entity.statistics.*;
import com.prometeo.application.entity.math.VectorUtils;

import java.util.*;

/**
 *
 * @author L Ojo: Falta agregar el metodo analisis ordinal
 */
public class ModeloMachineLearning<I> {
    private final Map<I, List<Double>> featureMatrix = new HashMap<>();

    public ModeloMachineLearning(Set<AnalysisUnit<I>> analysisUnits) {
        if (analysisUnits.isEmpty()) {
            throw new IllegalArgumentException("Analysis units cannot be empty");
        }

        DataFrame<I> dataFrame = new DataFrame<>(analysisUnits);

        for (AnalysisUnit<I> analysisUnit : analysisUnits) {
            List<Double> features = new ArrayList<>();

            // Nominal → one-hot encoding
            for (Nominal variable : analysisUnit.getNominalVariables()) {
                Set<String> possibleValues = dataFrame.getNominalValues(variable);
                features.addAll(oneHotEncode(possibleValues, variable));
            }


            /*
            // Ordinal → numerical value
            for (Ordinal variable : analysisUnit.getOrdinalVariables()) {
                features.add(variable.getValue());
            }*/

            // Continuous → numerical value
            for (Continuous variable : analysisUnit.getContinuousVariables()) {
                features.add(variable.getValue());
            }

            // Discrete → numerical value
            for (Discrete variable : analysisUnit.getDiscreteVariables()) {
                features.add(variable.getValue());
            }

            featureMatrix.put(analysisUnit.getId(), features);
        }
    }

    public List<Double> oneHotEncode(Set<String> possibleValues, Nominal variable) {
        List<Double> features = new ArrayList<>(possibleValues.size());

        List<String> sortedValues = new ArrayList<>(possibleValues);
        Collections.sort(sortedValues);

        for (String possibleValue : sortedValues) {
            features.add(
                    variable.getValue().contains(possibleValue) ? 1.0 : 0.0
            );
        }

        return features;
    }



    public void imprimirMatrizCaracteristicas() {

    }
}