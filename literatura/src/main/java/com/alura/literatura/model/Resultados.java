package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Resultados {
    @JsonAlias("title")
    private String titulo;
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @JsonAlias("authors")
    private List<Authors> autores;
    public List<Authors> getAutores() {return autores;}
    public void setAutores(List<Authors> autor) {this.autores = autor;}

    @JsonAlias("languages")
    private List<String> idioma;
    public List<String> getIdioma() {return idioma;}
    public void setIdioma(List<String> idioma) {this.idioma = idioma;}

    @JsonAlias("download_count")
    private String numeroDeDescargas;
    public String getNumeroDeDescargas() {return numeroDeDescargas;}
    public void setNumeroDeDescargas(String numeroDeDescargas) {this.numeroDeDescargas = numeroDeDescargas;}

    @Override
    public String toString() {
        return "Resultados{" +
                "titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idioma=" + idioma +
                ", numeroDeDescargas='" + numeroDeDescargas + '\'' +
                '}';
    }
}



