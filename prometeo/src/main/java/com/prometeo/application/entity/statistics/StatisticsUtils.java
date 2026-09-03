package com.prometeo.application.entity.statistics;

import com.prometeo.application.entity.math.MathUtils;

/**
 * Utility class for statistical calculations.
 */
public class StatisticsUtils {

    /**
     * Calculates the variance of a dataset.
     *
     * @param data the array of numerical values used to calculate the variance
     * @param isSample if true, calculates the sample variance (dividing by n - 1);
     *                 if false, calculates the population variance (dividing by n)
     * @return the calculated variance of the dataset
     * @throws IllegalArgumentException if the array is null or contains fewer than two elements
     */
    public static double calculateVariance(double[] data, boolean isSample) {
        if (data == null || data.length < 2) {
            throw new IllegalArgumentException(
                    "The array must contain at least two elements."
            );
        }

        double average = MathUtils.avg(data);
        double sumOfSquares = 0.0;

        for (double value : data) {
            sumOfSquares += Math.pow(value - average, 2);
        }

        return isSample
                ? sumOfSquares / (data.length - 1)
                : sumOfSquares / data.length;
    }

    /**
     * Calculates the standard deviation of a dataset.
     *
     * @param data the array of numerical values used to calculate the standard deviation
     * @param isSample if true, calculates the sample standard deviation;
     *                 if false, calculates the population standard deviation
     * @return the calculated standard deviation of the dataset
     * @throws IllegalArgumentException if the array is null or contains fewer than two elements
     */
    public static double calculateStandardDeviation(double[] data, boolean isSample) {
        return Math.sqrt(calculateVariance(data, isSample));
    }

    /**
     * Normalizes the data using min-max normalization.
     *
     * @param data the array of numerical values to normalize
     * @return a new array containing the normalized values in the range [0, 1]
     * @throws IllegalArgumentException if the array is null or empty,
     *                                  or if all values are equal
     */
    public static double[] minMaxNormalization(double[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException(
                    "The array cannot be null or empty."
            );
        }

        double min = data[0];
        double max = data[0];

        for (double value : data) {
            if (value < min) {
                min = value;
            }

            if (value > max) {
                max = value;
            }
        }

        if (min == max) {
            throw new IllegalArgumentException(
                    "All values are equal; normalization cannot be performed."
            );
        }

        double[] normalizedData = new double[data.length];

        for (int i = 0; i < data.length; i++) {
            normalizedData[i] = (data[i] - min) / (max - min);
        }

        return normalizedData;
    }
}