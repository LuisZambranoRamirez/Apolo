package com.prometeo.application.entity.estadistica;

import java.util.*;

public abstract class UnidadAnalisis {  
    private final String nombre;
    private final Map<String, Variable<?>> subtipoVariable = new HashMap<>();

    public UnidadAnalisis(String nombre) {
        this.nombre = nombre;
    }

    public boolean addVariable(Set<Variable<?>> variables) {
        String nombre = variables.stream().findFirst();
        if (subtipoVariable.containsKey()) {}
        subtipoVariable.put(nombre, variables);
    }
    
    protected abstract void registrarVariables();

}
