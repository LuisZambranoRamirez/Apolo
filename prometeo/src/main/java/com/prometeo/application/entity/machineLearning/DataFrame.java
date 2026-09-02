package com.prometeo.application.entity.machineLearning;

import com.prometeo.application.entity.estadistica.UnidadAnalisis;
import com.prometeo.application.entity.estadistica.Variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataFrame {
    private final Set<UnidadAnalisis<?>> unidadAnalises;
    private final Set<String> attributesNames;

    public DataFrame(Set<UnidadAnalisis<?>> unidadAnalises) {
        UnidadAnalisis<?> claseReferencia = unidadAnalises.iterator().next();

        for (UnidadAnalisis<?> unidad : unidadAnalises) {
            if (!claseReferencia.hasSameStructure(unidad)) {
                throw new IllegalArgumentException("Error: Todas las unidades de análisis deben ser de la misma clase. Se esperaba: " + claseReferencia);
            }
        }

        this.unidadAnalises = unidadAnalises;
        this.attributesNames = claseReferencia.getAttributeNames();
    }

    public List<Variable<?>> getVariable(String attribute) {
        if (attribute == null || !attributesNames.contains(attribute)) {
            return List.of();
        }

        List<Variable<?>> variables = new ArrayList<>(unidadAnalises.size());

        for (UnidadAnalisis<?> unidad : unidadAnalises) {
            variables.add(unidad.getVariable(attribute).orElseThrow());

        }

        return variables;
    }


}