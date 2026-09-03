package com.prometeo.application.entity.statistics;

import com.prometeo.application.entity.math.MathUtils;

/**
 *
 * @author L
 */
public class EstadisticaUtils {
    
    /**
    * Calcula la varianza de un conjunto de datos.
    *
    * @param data        arreglo de valores numéricos sobre los cuales se calculará la varianza.
    * @param esMuestral  si es true, calcula la varianza muestral (dividiendo entre n-1);
    *                    si es false, calcula la varianza poblacional (dividiendo entre n).
    * @return            la varianza calculada del arreglo de datos.
    * @throws IllegalArgumentException si el arreglo es null o tiene menos de dos elementos.
    */
    public static double calcularVarianza(double[] data, boolean esMuestral) {
        if (data == null || data.length < 2) {
            throw new IllegalArgumentException("El arreglo debe contener al menos dos elementos.");
        }

        double avg = MathUtils.avg(data);
        double sumaCuadrados = 0.0;

        for (double num : data) {
            sumaCuadrados += Math.pow(num - avg, 2);
        }

        return esMuestral ? sumaCuadrados / (data.length - 1) : sumaCuadrados / data.length;
    }

    /**
    * Calcula la desviación estándar de un conjunto de datos.
    *
    * @param data        arreglo de valores numéricos sobre los cuales se calculará la desviación estándar.
    * @param esMuestral  si es true, calcula la desviación estándar muestral;
    *                    si es false, calcula la desviación estándar poblacional.
    * @return            la desviación estándar calculada del arreglo de datos.
    * @throws IllegalArgumentException si el arreglo es null o tiene menos de dos elementos (se propaga desde calcularVarianza).
    */
    public static double calcularDesviacionEstandar(double[] data, boolean esMuestral) {
        return Math.sqrt(calcularVarianza(data, esMuestral));
    }
    
    public static double[] minMaxEstandarizacion(double[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser null o vacío.");
        }

        double min = data[0]; 
        double max = data[0];

        for (double num : data) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        if (min == max) {
            throw new IllegalArgumentException("Todos los valores son iguales, no se puede normalizar.");
        }

        double[] normalizado = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            normalizado[i] = (data[i] - min) / (max - min);
        }

        return normalizado;
    }
    
}
