package com.grupo9.ArbolandoRosario.Repositorio;

import com.grupo9.ArbolandoRosario.Entidades.TipoArbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoArbolDAO extends JpaRepository<TipoArbol, Long>{

}
