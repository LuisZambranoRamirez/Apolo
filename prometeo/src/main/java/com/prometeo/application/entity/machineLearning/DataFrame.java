package com.prometeo.application.entity.machineLearning;

import com.prometeo.application.entity.estadistica.UnidadAnalisis;
import com.prometeo.application.entity.estadistica.Variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataFrame {
    private final Set<UnidadAnalisis<?>> unidadAnalises;
    private final Set<String> namesAttributes;

    public DataFrame(Set<UnidadAnalisis<?>> unidadAnalises) {
        UnidadAnalisis<?> claseReferencia = unidadAnalises.iterator().next();

        for (UnidadAnalisis<?> unidad : unidadAnalises) {
            if (!claseReferencia.getClassName().equals(unidad.getClassName())) {
                throw new IllegalArgumentException("Error: Todas las unidades de análisis deben ser de la misma clase. Se esperaba: " + claseReferencia);
            }
        }

        this.unidadAnalises = unidadAnalises;
        this.namesAttributes = claseReferencia.getAttributeNames();
    }

    public List<Variable<?>> getVariable(String attribute) {
        List<Variable<?>> a = new ArrayList<>(unidadAnalises.size());
        for (UnidadAnalisis<?> unidad : unidadAnalises) {
            a.add(unidad.getVariable(attribute));
        }
        return a;
    }

    public Set<String> getUniqueValues(String attributes) {
        Set<String> a = new HashSet<>(unidadAnalises.size());
        for (UnidadAnalisis<?> unidad : unidadAnalises) {
            for (String )
            a.add(unidad.getVariable(attributes).getValue());
        }
        return a;
    }

}
