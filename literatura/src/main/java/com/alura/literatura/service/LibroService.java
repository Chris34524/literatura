package com.alura.literatura.service;

import com.alura.literatura.model.DatosLibro;
import com.alura.literatura.model.Libro;
import com.alura.literatura.model.Resultados;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;
        public void guardaLibro(Libro resultados){
                Libro libro = new Libro(resultados);
                libroRepository.save(libro);

    }
}
