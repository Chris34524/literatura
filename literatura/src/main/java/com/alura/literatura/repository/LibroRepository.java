package com.alura.literatura.repository;

import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT s FROM Libro s WHERE s.anioNacimiento <= :anioBusqueda AND s.anioMuerte >= :anioBusqueda")
    List<Libro> busquedaAutorVivo(int anioBusqueda);
}