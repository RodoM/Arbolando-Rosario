package com.grupo9.ArbolandoRosario.Repositorio;

import com.grupo9.ArbolandoRosario.Entidades.Arbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbolDAO extends JpaRepository<Arbol, Long> {

    Arbol findByNombreIgnoreCase(String nombre);

    Arbol findByNombreCientificoIgnoreCase(String nombreCientifico);
}
