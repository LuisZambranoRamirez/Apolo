package com.prometeo.application.entity.math;

/**
 *
 * @author L
 */
public class VectorUtils {
    public static double calculateCosineSimilarity(double [] vector1, double [] vector2) {

        if (vector1.length != vector2.length) {
            if (vector1.length < vector2.length) {
                vector1 = ajustarSizeVector(vector1, vector2.length);
            } else {
                vector2 = ajustarSizeVector(vector2, vector1.length);
            }            
        }

        double dotProduct = calculateDotProduct(vector1, vector2);
        
        double magnitude1 = calculateMagnitude(vector1);
        if (magnitude1 == 0) {
            throw new IllegalArgumentException("Vector magnitude must not be zero.");
        }
        
        double magnitude2 = calculateMagnitude(vector2);
        if (magnitude2 == 0) {
            throw new IllegalArgumentException("Vector magnitude must not be zero.");
        }

        return dotProduct / (magnitude1 * magnitude2);
    }
    
    // Nota: Evaluar un mejor nombre para esta funcion que devuelve un array 
    // con el tamano que se le paso como parametro
    // Ojo: el length debe ser mayor al tamano del vector que recibe
    private static double[] ajustarSizeVector(double[] vector, int length) {
        double[] newVector = new double[length];
        System.arraycopy(vector, 0, newVector, 0, vector.length);
        return newVector;
    }
    
    public static double calculateMagnitude(double [] vector) {
        double sum = 0.0;
        for (double v : vector) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }
            
    public static double calculateDotProduct(double [] vector1, double [] vector2) {
        double sum = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            sum+= vector1[i] * vector2[i];            
        }
        return sum;
    }
  
}
