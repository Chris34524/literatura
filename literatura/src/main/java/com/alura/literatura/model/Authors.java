package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Authors{
    @JsonAlias("name")
    private String autor;
    public String getAutor() {return autor;}
    public void setAutor(String autor) {this.autor = autor;}

    @JsonAlias("birth_year")
    private Integer anioNacimiento;
    public Integer getAnioNacimiento() {return anioNacimiento;}
    public void setAnioNacimiento(Integer anioNacimiento) {this.anioNacimiento = anioNacimiento;}

    @JsonAlias("death_year")
    private Integer anioMuerte;
    public Integer getAnioMuerte() {return anioMuerte;}
    public void setAnioMuerte(Integer anioMuerte) {this.anioMuerte = anioMuerte;}

    @Override
    public String toString() {
        return "Authors{" +
                "autor='" + autor + '\'' +
                ", anioNacimiento='" + anioNacimiento + '\'' +
                ", anioMuerte='" + anioMuerte + '\'' +
                '}';
    }
}
