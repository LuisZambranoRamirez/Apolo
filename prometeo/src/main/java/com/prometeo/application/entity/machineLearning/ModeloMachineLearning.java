package com.prometeo.application.entity.machineLearning;

import com.prometeo.application.entity.math.VectorUtils;
import com.prometeo.application.entity.statistics.*;

import java.util.*;

/**
 *
 * @author L Ojo: Falta agregar el metodo analisis ordinal
 */
public class ModeloMachineLearning<I> {
    private final Map<AnalysisUnit<I>, List<Double>> featureMatrix = new HashMap<>();
    private final DataFrame<I> dataFrame;

    public ModeloMachineLearning(Set<AnalysisUnit<I>> analysisUnits) {
        if (analysisUnits.isEmpty()) {
            throw new IllegalArgumentException("Analysis units cannot be empty");
        }

        for (AnalysisUnit<I> analysisUnit : analysisUnits) {
            analysisUnit.initializeVariables();
        }

        this.dataFrame = new DataFrame<>(analysisUnits);

        for (AnalysisUnit<I> analysisUnit : analysisUnits) {
            List<Double> features = convertToFeatureVector(analysisUnit);
            featureMatrix.put(analysisUnit, features);
        }
    }

    private List<Double> convertToFeatureVector(AnalysisUnit<I> analysisUnit) {
        List<Double> features = new ArrayList<>();

        // Nominal → one-hot encoding
        for (Nominal variable : analysisUnit.getNominalVariables()) {
            Set<String> possibleValues = dataFrame.getNominalValues(variable);
            features.addAll(MlUtils.oneHotEncode(possibleValues, variable));
        }

        /*
        // Ordinal → numerical value
        for (Ordinal variable : analysisUnit.getOrdinalVariables()) {
            features.add(variable.getValue());
        }
        */

        // Continuous → numerical value
        for (Continuous variable : analysisUnit.getContinuousVariables()) {
            features.add(variable.getValue());
        }

        // Discrete → numerical value
        for (Discrete variable : analysisUnit.getDiscreteVariables()) {
            features.add(variable.getValue());
        }

        return features;
    }

    public List<AnalysisUnit<I>> findSimilarAnalysisUnits(AnalysisUnit<I> analysisUnit) {
        if (!dataFrame.haveSameStructure(analysisUnit)) {
            throw new IllegalArgumentException(
                    "The analysis unit has a different structure."
            );
        }

        List<Double> targetFeatures = convertToFeatureVector(analysisUnit);

        List<Map.Entry<AnalysisUnit<I>, Double>> similarAnalysisUnits = new ArrayList<>();

        for (Map.Entry<AnalysisUnit<I>, List<Double>> entry : featureMatrix.entrySet()) {

            AnalysisUnit<I> currentAnalysisUnit = entry.getKey();
            List<Double> currentFeatures = entry.getValue();

            double similarity = VectorUtils.calculateCosineSimilarity(
                    targetFeatures,
                    currentFeatures
            );

            similarAnalysisUnits.add(
                    new AbstractMap.SimpleEntry<>(
                            currentAnalysisUnit,
                            similarity
                    )
            );
        }

        // Highest similarity first
        similarAnalysisUnits.sort(
                Map.Entry.<AnalysisUnit<I>, Double>comparingByValue().reversed()
        );

        List<AnalysisUnit<I>> result = new ArrayList<>();

        for (Map.Entry<AnalysisUnit<I>, Double> entry : similarAnalysisUnits) {
            result.add(entry.getKey());
        }

        return result;
    }

    public void imprimirMatrizCaracteristicas() {

    }
}