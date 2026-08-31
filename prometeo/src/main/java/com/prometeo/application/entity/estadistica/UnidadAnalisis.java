package com.prometeo.application.entity.estadistica;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author L
 * Nota: Es posible mejorar el rendimiento usando numeros en el hasmap en vez de 
 * metadatos, por ejemplo, si es un escalar la variable lo guradamos con un indice
 * positivo y si no lo es, lo guardamos con un indice negativo, luego lo transformariamos
 * posteriormente para su uso. De esta manera se evita crear la clase metadata por cada
 * variable guardada. El contrabeneficio seria que es mas dificil de leer el 
 * codigo para asi en un futuro poder darle mantenimiento
 */
public abstract class UnidadAnalisis {  
    private final String nombre;
    private final HashMap<String, Metadata> indicePorNombre = new HashMap<>();

    private final ArrayList<Variable> listVariables = new ArrayList<>();
    private final ArrayList<Variable[]> vectorVariables = new ArrayList<>();


    public UnidadAnalisis(String nombre) {
        this.nombre = nombre;
    }

    protected boolean addVariable(Variable variable) {
        if (variable == null || indicePorNombre.containsKey(variable.getNombre())) {
            return false;
        }
        int indice = listVariables.size();
        boolean isVariableAdd = listVariables.add(variable);
        if (isVariableAdd) {
            indicePorNombre.put(variable.getNombre(), new Metadata(true, indice));
        }
        return isVariableAdd;        
    }
    
    protected boolean addVectorVariable(String nombre, Variable[] variable) {
        if (variable == null || indicePorNombre.containsKey(nombre)) {
            return false;
        }
        // NOTA: Evaluar la factibilidad de colocarle en el nombre null.
        for (Variable var : variable) {
            var.setNombre(nombre);
        }
        int indice = vectorVariables.size();
        boolean isVariableAdd = vectorVariables.add(variable);
        if (isVariableAdd) {
            indicePorNombre.put(nombre, new Metadata(false, indice));
        }
        return isVariableAdd; 
    }
    
    public Variable getVariableTypeEscalar(String nombre) {
        if (nombre == null) {
            return null;
        }
        
        Metadata metadata = indicePorNombre.get(nombre);
        if (metadata == null) return null;
        
        int indice = metadata.indice;
        
        if (metadata.isEscalar) {
            return listVariables.get(indice);
        }
        
        return null;
    }
    
    public Variable[] getVariableTypeVector(String nombre) {
        if (nombre == null) {
            return null;
        }
        
        Metadata metadata = indicePorNombre.get(nombre);
        if (metadata == null) return null;
        
        int indice = metadata.indice;
        
        if (metadata.isEscalar) {
            return null;
        }
        return vectorVariables.get(indice);
    }
    
    public Variable[] getVariable(String nombre) {
        if (nombre == null) {
            return null;
        }
        
        Metadata metadata = indicePorNombre.get(nombre);
        if (metadata == null) return null;
        
        int indice = metadata.indice;
        
        if (metadata.isEscalar) {
            return new Variable[] { listVariables.get(indice) };
        }
        return vectorVariables.get(indice);
    }
              
    protected boolean removeVariable(String nombre) {
        Metadata metadata = indicePorNombre.get(nombre);
        if (metadata == null) {
            return false;
        }

        if (metadata.isEscalar()) {
            listVariables.remove(metadata.indice);
        } else {
            vectorVariables.remove(metadata.indice);
        }

        indicePorNombre.remove(nombre);
        
        return true;
    }
        
    public int getNumVariables() {
        return indicePorNombre.size();
    }
    
    protected abstract void registrarVariables();
    
    public String[] getListVariableNames() {
        return indicePorNombre.keySet().toArray(new String[0]);
    }

    public HashMap<String, Metadata> getIndicePorNombre() {
        return indicePorNombre;
    }

    public ArrayList<Variable> getListVariables() {
        return listVariables;
    }

    public ArrayList<Variable[]> getVectorVariables() {
        return vectorVariables;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
      
    private record Metadata(boolean isEscalar, int indice) {
        public Metadata {
            if (indice < 0) {
                throw new RuntimeException("EL indice debe ser positivo");
            }
        }        
    }
    
}
