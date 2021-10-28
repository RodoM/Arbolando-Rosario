package com.grupo9.ArbolandoRosario.Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo9.ArbolandoRosario.Entidades.Sesion;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionDAO extends JpaRepository<Sesion, Long>{

}
