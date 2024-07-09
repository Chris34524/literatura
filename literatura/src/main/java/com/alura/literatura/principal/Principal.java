package com.alura.literatura.principal;

import java.util.Scanner;
import java.util.*;

import com.alura.literatura.model.DatosLibro;
import com.alura.literatura.model.Libro;
import com.alura.literatura.model.Resultados;
import com.alura.literatura.repository.LibroRepository;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;
import com.alura.literatura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private LibroService libroService;
    private List<Libro> libros;

    @Autowired
    public Principal(LibroRepository repository, LibroService libroService) {
        this.repositorio = repository;
        this.libroService = libroService;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en determinado año
                    5 - Listar libros por idioma                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro que deseas: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);

        Optional<Resultados> resultadoBuscado = datos.getResultados().stream()
                .filter(e->e.getTitulo().contains(nombreLibro))
                .findFirst();

        if(resultadoBuscado.isEmpty()){
            System.out.println("No existe el libro!!!");
        }else{
            try {
                Libro resultado = resultadoBuscado.map(Libro::new).orElse(null); //Transformo de Optional<Resultados> a Libro por medio de un constructor en la clase Libro que espera un objeto del tipo Resultados
                libroService.guardaLibro(resultado);
                System.out.println("Libro registrado en la BD");
            }catch (Exception e){
                System.out.println("El libro ya existe en la BD");
            }
        }
    }
    private void listarLibrosRegistrados() {
        libros = repositorio.findAll();
        System.out.println("*********** Libros ***********");
        libros.forEach(s->
                System.out.println(
                        "Título: " + s.getTitulo() + "\n"+
                        "Autor: " + s.getAutores() + "\n"+
                        "Idioma: " + s.getIdioma() + "\n"+
                        "Número de descargas: " + s.getNumeroDeDescargas() + "\n"));
        System.out.println("*******************************");
    }
    private void listarAutoresRegistrados() {
        libros = repositorio.findAll();
        System.out.println("*********** Autores ***********");
        libros.forEach(s->
                System.out.println("Autor: " + s.getAutores() + "\n"));
                System.out.println("*******************************");
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año que desea saber cuales autores seguían con vida");
        var anioBusqueda = teclado.nextInt();
        //libros = repositorio.findAll();
        System.out.println("*********** Autores con vida en el año " + anioBusqueda + " ***********");
        List<Libro> filtroAutores = repositorio.busquedaAutorVivo(anioBusqueda);
        filtroAutores.forEach(s->
                System.out.println("Autor: " + s.getAutores() + "\n" +
                               "Nacimiento: " + s.getAnioNacimiento() + "\n" +
                               "Muerte: " + s.getAnioMuerte() + "\n"));
                System.out.println("*******************************");
    }

    private void listarLibrosPorIdioma() {
        libros = repositorio.findAll();
        System.out.println("*********** Lista de libros por idioma ***********");
        libros.stream()
                .sorted(Comparator.comparing(Libro::getIdioma))
                .forEach(s->
                        System.out.println(
                                "Título: " + s.getTitulo() + "\n"+
                                "Autor: " + s.getAutores() + "\n"+
                                "Idioma: " + s.getIdioma() + "\n"+
                                "Número de descargas: " + s.getNumeroDeDescargas() + "\n"));
        System.out.println("*******************************");
    }
}

