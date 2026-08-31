package com.prometeo.application.entity.machineLearning;

import com.prometeo.application.entity.estadistica.UnidadAnalisis;
import com.prometeo.application.entity.estadistica.Variable;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author L
 */
public class DataFrame {
    private UnidadAnalisis[] listaUnidades;
    private String[] listaNombresVariables;
    private Variable[][] matrizDatos;
    
    private HashMap<String, Integer> mapaIndiceVariables = new HashMap<>();
    
    /**
    * Constructor vacío. No inicializa datos.
    */
    public DataFrame() {
    }

    /**
    * Constructor que crea un DataFrame a partir de una lista de unidades de análisis.
    * Si se solicita uniformidad, se valida que todas las variables con el mismo nombre
    * tengan el mismo subtipo en todas las unidades.
    *
    * @param listaUnidades        Arreglo de unidades de análisis
    */
    public DataFrame(UnidadAnalisis[] listaUnidades) {
        this.listaUnidades = listaUnidades;
        buildFrame();
        printData();
    }

    /**
    * Construye el DataFrame internamente:
    * 1. Extrae nombres únicos de variables
    * 2. Valida uniformidad si corresponde
    * 3. Construye la matriz tridimensional de datos
    */
    private void buildFrame() {
        // Obtener nombres únicos de variables
        HashSet<String> nombresUnicosVariables = new HashSet<>();
        for (UnidadAnalisis unidad : listaUnidades) {
            for (String variableName : unidad.getListVariableNames()) {
                if (nombresUnicosVariables.add(variableName)) {
                    mapaIndiceVariables.put(variableName, mapaIndiceVariables.size());
                }
            }
        }
        this.listaNombresVariables = nombresUnicosVariables.toArray(new String[0]);
           
        // Crear la matriz de datos
        int numColumnas = getNumVariables();
        int numFilas = getNumUnidades();
        
        this.matrizDatos = new Variable[numFilas][numColumnas];

        // Llenar la matriz 
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                Variable variable = listaUnidades[i].getVariableTypeEscalar(listaNombresVariables[j]);
                if (variable == null) {
                    continue;
                }
                matrizDatos[i][j] = variable;
            }
        }
        
        // Validar uniformidad de subtipos de variable, si se solicita
        validarUniformidadVariables();
    }
    
    /**
    * Valida que todas las variables con el mismo nombre tengan un subtipo uniforme
    * en todas las unidades de análisis del DataFrame.
    * 
    * Lanza una excepción si se detectan variables con subtipos inconsistentes.
    */
    private void validarUniformidadVariables() {
        
        for (int i = 0; i < getNumVariables(); i++) {
            Variable[] variables = getVariableValuesList(i);
            boolean esUniforme = Variable.isUniformVariableSubType(variables);   
            
            if (!esUniforme) {
                throw new IllegalStateException("Variables no uniformes para el nombre: " + variables[0].getNombre());
            } 
            
        }      
    }
              
    /**
    * Obtiene todas las instancias de variables asociadas a un nombre de variable
    * específico en todas las unidades del DataFrame.
    * <p>
    * Este método devuelve un arreglo plano (1D) que contiene todas las instancias
    * de {@code Variable} que corresponden a la variable con el nombre dado,
    * independientemente de a qué unidad pertenecen.
    * <p>
    * Si el nombre de la variable no existe en el DataFrame, se devuelve {@code null}.
    *
    * @param nombreVariable Nombre de la variable cuya columna se desea obtener.
    * @return Un arreglo de {@code Variable} que contiene todas las instancias
    *         de dicha variable en el DataFrame, o {@code null} si el nombre no existe.
    */
    public Variable[] getVariableValuesList(String nombreVariable) {
        Integer posicion = getPosicionVariable(nombreVariable);
        return (posicion == null) ? null : getVariableValuesList(posicion);
    }

    /**
    * Obtiene todas las instancias de variables asociadas a un índice de columna
    * específico en todas las unidades del DataFrame.
    * <p>
    * Este método recorre la columna indicada por su índice en la matriz de datos,
    * y recopila todas las instancias de {@code Variable} en un arreglo plano (1D).
    * <p>
    * Si el índice es inválido (fuera de rango), se devuelve {@code null}.
    *
    * @param indiceVariable Índice de la variable a recuperar.
    * @return Un arreglo de {@code Variable} con todas las instancias de la variable
    *         en todas las unidades, o {@code null} si el índice no es válido.
    */
    public Variable[] getVariableValuesList(int indiceVariable) {
        if (!indiceValido(indiceVariable)) {
            return null;
        }
        
        Variable[] columnaVariables = new Variable[getNumUnidades()];
        
        for (int i = 0; i < getNumUnidades(); i++) {
            columnaVariables[i] = matrizDatos[i][indiceVariable];
        }
        
        return columnaVariables;
    }
    
    public Integer getPosicionVariable(String nombreVariable) {
        return mapaIndiceVariables.get(nombreVariable);
    }
      
    /**
    * Verifica si un índice de variable es válido dentro del DataFrame.
    *
    * @param indice Índice a verificar.
    * @return {@code true} si el índice está dentro del rango de variables disponibles,
    *         {@code false} en caso contrario.
    */
    private boolean indiceValido(int indice) {
        return indice >= 0 && indice < getNumVariables();
    }

    /**
    * @return Número total de unidades de análisis en el DataFrame.
    */
    public int getNumUnidades() {
        return listaUnidades.length;
    }
    
    /**
    * @return Número de variables únicas en el DataFrame.
    */
    public int getNumVariables() {
        return listaNombresVariables.length;
    }
    
    public void printData() {
        if (matrizDatos == null || matrizDatos.length == 0) {
            System.out.println("Matriz vacía.");
            return;
        }

        int filas = matrizDatos.length;
        int columnas = matrizDatos[0].length;

        // 1. Calcular el ancho máximo por columna (entre nombre y valores)
        int[] anchos = new int[columnas];
        for (int j = 0; j < columnas; j++) {
            String nombre = matrizDatos[0][j].getNombre();
            anchos[j] = nombre.length(); // empezamos con la longitud del nombre

            for (int i = 0; i < filas; i++) {
                int largoValor = String.valueOf(matrizDatos[i][j].getValor()).length();
                anchos[j] = Math.max(anchos[j], largoValor);
            }
        }

        // 2. Imprimir encabezados
        System.out.print("| Fila |");
        for (int j = 0; j < columnas; j++) {
            String nombre = matrizDatos[0][j].getNombre();
            System.out.printf(" %-" + anchos[j] + "s |", nombre);
        }
        System.out.println();

        // 3. Separador
        System.out.print("|------|");
        for (int ancho : anchos) {
            System.out.print("-".repeat(ancho + 2) + "|");
        }
        System.out.println();

        // 4. Imprimir filas con datos
        for (int i = 0; i < filas; i++) {
            System.out.printf("|  %-4d|", i);
            for (int j = 0; j < columnas; j++) {
                Object valor = matrizDatos[i][j].getValor();
                System.out.printf(" %-" + anchos[j] + "s |", valor);
            }
            System.out.println();
        }
    }

    public UnidadAnalisis[] getListaUnidades() {
        return listaUnidades;
    }

    public String[] getListaNombresVariables() {
        return listaNombresVariables;
    }

    public Variable[][] getMatrizDatos() {
        return matrizDatos;
    }

    public HashMap<String, Integer> getMapaIndiceVariables() {
        return mapaIndiceVariables;
    }
    
}
