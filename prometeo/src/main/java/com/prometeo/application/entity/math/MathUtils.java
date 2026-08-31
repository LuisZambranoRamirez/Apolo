package com.prometeo.application.entity.math;

/**
 *
 * @author L
 */
public class MathUtils {
    public static double avg(double[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede estar vacío ni ser null.");
        }

        double suma = 0.0;
        for (double valor : data) {
            suma += valor;
        }

        return suma / data.length;
    }

}
