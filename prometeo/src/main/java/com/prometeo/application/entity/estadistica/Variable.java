package com.prometeo.application.entity.estadistica;

/**
 *
 * @author L
 * @param <E>
 */
public abstract sealed class Variable<E> permits Ordinal, Continua, Discreta, Nominal {
    private final String nombre;
    private final E valor;
    private final TipoVariable tipoVariable;
    private final SubtipoVariable subtipoVariable;

    public Variable(String nombre, SubtipoVariable subtipoVariable, E valor) {
        this.nombre = nombre;
        this.valor = valor;
        this.subtipoVariable = subtipoVariable;

        this.tipoVariable = switch (subtipoVariable) {
            case NOMINAL, ORDINAL -> TipoVariable.CUALITATIVA;
            case DISCRETA, CONTINUA -> TipoVariable.CUANTITATIVA;
        };
    }

    public String getNombre() {
        return nombre;
    }

    public E getValor() {
        return valor;
    }
    /**
    * Verifica si todas las variables en el arreglo comparten el mismo subtipo.
    *
    * <p>
    * Este método compara el subtipo (obtenido mediante {@code getSubtipo()}) del primer elemento del arreglo
    * con el de los elementos restantes. Si todos los subtipos son iguales, se considera que la colección
    * es uniforme.
    * </p>
    *
    * <p>
    * Si el arreglo contiene cero o un elemento, se considera automáticamente uniforme.
    * </p>
    *
    * <b>Nota:</b> Este método no maneja valores {@code null} en el arreglo ni en los elementos del mismo.
    * Se asume que todos los elementos son no nulos.
    *
    * @param variables Arreglo de objetos {@code Variable} a evaluar.
    * @return {@code true} si todas las variables tienen el mismo subtipo, o si el arreglo tiene 0 o 1 elemento;
    *         {@code false} si se encuentra al menos un subtipo diferente.
    *
    * @throws NullPointerException si el arreglo o alguno de sus elementos es {@code null}.
    */
    public static boolean isUniformVariableSubType(Variable[] variables) {
        if (variables.length <= 1) {
            return true;
        }
        String subType = variables[0].getSubtipoVariable();
        for (int i = 1; i < variables.length; i++) {
            if (!subType.equals(variables[i].getSubtipoVariable())) {
                return false;
            }
        }

        return true;
    }

    public TipoVariable getTipoVariable() {
        return tipoVariable;
    }

    public SubtipoVariable getSubtipoVariable() {
        return subtipoVariable;
    }

    public enum SubtipoVariable {
        NOMINAL,
        ORDINAL,
        DISCRETA,
        CONTINUA
    }

    public enum TipoVariable {
        CUALITATIVA,
        CUANTITATIVA
    }
}
