package com.prometeo.application.entity.machineLearning;

import com.prometeo.application.entity.estadistica.UnidadAnalisis;
import com.prometeo.application.entity.estadistica.Variable;
import com.prometeo.application.entity.math.VectorUtils;

import java.util.ArrayList;

/**
 *
 * @author L Ojo: Falta agregar el metodo analisis ordinal
 */
public class ModeloMachineLearning {
    private DataFrame dataFrameProcesado;
    private double[][] matrizCaracteristicas;

    public ModeloMachineLearning() {
    }

    public ModeloMachineLearning(UnidadAnalisis[] unidadAnalisis, UnidadAnalisis unidadAnalizar) {
         // Crear nuevo arreglo con tamaño +1
        UnidadAnalisis[] nuevoArreglo = new UnidadAnalisis[unidadAnalisis.length + 1];

        // Agregar unidadAnalizar como primer elemento
        nuevoArreglo[0] = unidadAnalizar;

        // Copiar el resto de unidadAnalisis
        System.arraycopy(unidadAnalisis, 0, nuevoArreglo, 1, unidadAnalisis.length);
        
        this.dataFrameProcesado = new DataFrame(nuevoArreglo);
        init();
    }
    
    public Cancion[] recomendarCanciones() {
        // Referencia al vector de características de la canción base
        double[] vectorReferencia = matrizCaracteristicas[0];
        int numComparaciones = matrizCaracteristicas.length - 1;

        // Obtener todas las unidades (canciones) procesadas
        UnidadAnalisis[] unidades = dataFrameProcesado.getListaUnidades();

        // Arreglos para almacenar canciones y sus puntuaciones
        Cancion[] cancionesAComparar = new Cancion[numComparaciones];
        double[] puntuacionesSimilitud = new double[numComparaciones];

        // Llenar los arreglos usando el array de unidades
        for (int i = 1; i < matrizCaracteristicas.length; i++) {
            cancionesAComparar[i - 1] = (Cancion) unidades[i];
            puntuacionesSimilitud[i - 1] = VectorUtils.calculateCosineSimilarity(vectorReferencia, matrizCaracteristicas[i]);
        }

        // Ordenación por burbuja en orden descendente de similitud
        for (int i = 0; i < numComparaciones - 1; i++) {
            for (int j = i + 1; j < numComparaciones; j++) {
                if (puntuacionesSimilitud[j] > puntuacionesSimilitud[i]) {
                    // Intercambiar puntuaciones
                    double tempPuntuacion = puntuacionesSimilitud[i];
                    puntuacionesSimilitud[i] = puntuacionesSimilitud[j];
                    puntuacionesSimilitud[j] = tempPuntuacion;
                    // Intercambiar canciones
                    Cancion tempCancion = cancionesAComparar[i];
                    cancionesAComparar[i] = cancionesAComparar[j];
                    cancionesAComparar[j] = tempCancion;
                }
            }
        }

        return cancionesAComparar;
    }


    private void init() {
        int totalCanciones = dataFrameProcesado.getNumUnidades();
        int totalVariables = dataFrameProcesado.getNumVariables();

        // Creamos una lista de listas para guardar las características de cada canción
        ArrayList<Double>[] caracteristicasPorCancion = new ArrayList[totalCanciones];
        for (int i = 0; i < totalCanciones; i++) {
            caracteristicasPorCancion[i] = new ArrayList<>();
        }

        // Procesamos cada variable
        for (int i = 0; i < totalVariables; i++) {
            Variable[] valores = dataFrameProcesado.getVariableValuesList(i);
            String tipo = valores[0].getSubtipo();

            if (tipo.equals("Nominal")) {
                
                int[][] codificados = getEncoder(valores);
                for (int fila = 0; fila < totalCanciones; fila++) {
                    for (int valor : codificados[fila]) {
                        caracteristicasPorCancion[fila].add((double) valor);
                    }
                }
                
            } else if (tipo.equals("Continua") || tipo.equals("Discreta")) {
                
                for (int fila = 0; fila < totalCanciones; fila++) {
                    caracteristicasPorCancion[fila].add((double) valores[fila].getValor());
                }
                
            }
        }

        // Convertimos las listas a una matriz de características
        matrizCaracteristicas = new double[totalCanciones][];
        for (int i = 0; i < totalCanciones; i++) {
            ArrayList<Double> lista = caracteristicasPorCancion[i];
            matrizCaracteristicas[i] = new double[lista.size()];

            for (int j = 0; j < lista.size(); j++) {
                matrizCaracteristicas[i][j] = lista.get(j);
            }
        }
        

    }

    private int[][] getEncoder(Variable[] variables) {
        String[] data = new String[variables.length];
        for (int i = 0; i < variables.length; i++) {
            data[i] = (String) variables[i].getValor();
        }

        return MlUtils.oneHotEncoder(data);
    }
    
    public void imprimirMatrizCaracteristicas() {
    if (matrizCaracteristicas == null || matrizCaracteristicas.length == 0) {
        System.out.println("La matriz de características está vacía.");
        return;
    }

    System.out.println("Matriz de características:");

    for (int i = 0; i < matrizCaracteristicas.length; i++) {
        System.out.print("Canción " + (i + 1) + ": ");
        for (int j = 0; j < matrizCaracteristicas[i].length; j++) {
            System.out.printf("%.4f ", matrizCaracteristicas[i][j]); // 4 decimales
        }
        System.out.println();
    }
}

}