package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro{
    @JsonAlias("results")
    List<Resultados> resultados;
    public List<Resultados> getResultados() {return resultados;}
    public void setResultados(List<Resultados> resultados) {this.resultados = resultados;}

    @Override
    public String toString() {
        return "DatosLibro{" +
                "resultados=" + resultados +
                '}';
    }
}

