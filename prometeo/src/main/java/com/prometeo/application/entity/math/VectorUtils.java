package com.prometeo.application.entity.math;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author L
 */
public class VectorUtils {

    public static double calculateCosineSimilarity(List<Double> vector1, List<Double> vector2) {
        if (vector1.size() != vector2.size()) {
            if (vector1.size() < vector2.size()) {
                vector1 = ajustarSizeVector(vector1, vector2.size());
            } else {
                vector2 = ajustarSizeVector(vector2, vector1.size());
            }
        }

        double dotProduct = calculateDotProduct(vector1, vector2);

        double magnitude1 = calculateMagnitude(vector1);
        if (magnitude1 == 0) {
            throw new IllegalArgumentException(
                    "Vector magnitude must not be zero."
            );
        }

        double magnitude2 = calculateMagnitude(vector2);
        if (magnitude2 == 0) {
            throw new IllegalArgumentException(
                    "Vector magnitude must not be zero."
            );
        }

        return dotProduct / (magnitude1 * magnitude2);
    }

    private static List<Double> ajustarSizeVector(List<Double> vector, int length) {
        List<Double> newVector = new ArrayList<>(length);
        newVector.addAll(vector);

        while (newVector.size() < length) {
            newVector.add(0.0);
        }

        return newVector;
    }

    public static double calculateMagnitude(List<Double> vector) {
        double sum = 0.0;

        for (double v : vector) {
            sum += v * v;
        }

        return Math.sqrt(sum);
    }

    public static double calculateDotProduct(
            List<Double> vector1,
            List<Double> vector2
    ) {
        double sum = 0.0;

        for (int i = 0; i < vector1.size(); i++) {
            sum += vector1.get(i) * vector2.get(i);
        }

        return sum;
    }
}
