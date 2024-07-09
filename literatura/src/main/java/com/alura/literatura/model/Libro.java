package com.alura.literatura.model;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name="Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String autores;
    private Integer anioNacimiento;
    private Integer anioMuerte;
    private String idioma;
    private Integer numeroDeDescargas;

    public Libro(){}

    public Libro(Resultados resultados){
        this.titulo = resultados.getTitulo();
        this.autores = resultados.getAutores().stream().map(Authors::getAutor).findFirst().orElse(null);
        this.anioNacimiento = resultados.getAutores().stream().map(Authors::getAnioNacimiento).findFirst().orElse(null);
        this.anioMuerte = resultados.getAutores().stream().map(Authors::getAnioMuerte).findFirst().orElse(null);
        this.numeroDeDescargas = Integer.valueOf(resultados.getNumeroDeDescargas());
        this.idioma = resultados.getIdioma().toString().replace("[","").replace("]","");
    }

    public Libro(Libro resultados) {
        this.titulo = resultados.getTitulo();
        this.autores = resultados.getAutores();
        this.anioNacimiento = resultados.getAnioNacimiento();
        this.anioMuerte = resultados.getAnioMuerte();
        this.numeroDeDescargas = resultados.getNumeroDeDescargas();
        this.idioma = resultados.getIdioma().replace("[","").replace("]","");
    }

    public Long getId() {return Id;}
    public void setId(Long id) {Id = id;}

    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(Integer anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "Libro: " +
                "Id=" + Id +
                ", titulo='" + titulo + '\''+
                ", autor='" + autores + '\'' +
                ", anioNacimiento='" + anioNacimiento + '\'' +
                ", anioMuerte='" + anioMuerte + '\'' +
                ", idioma='" + idioma + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas;
    }
}
