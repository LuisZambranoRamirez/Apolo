package com.prometeo.application.entity.machineLearning;

import com.prometeo.application.entity.statistics.Nominal;

import java.util.*;

/**
 *
 * @author L
 */
public class MlUtils {
    public static List<Double> oneHotEncode(Set<String> possibleValues, Nominal variable) {
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
}
