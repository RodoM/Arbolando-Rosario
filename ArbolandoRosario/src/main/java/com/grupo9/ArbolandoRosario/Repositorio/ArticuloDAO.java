package com.grupo9.ArbolandoRosario.Repositorio;

import com.grupo9.ArbolandoRosario.Entidades.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ArticuloDAO extends JpaRepository<Articulo, Long>{
    List<Articulo> findByUsuarioMail(String mail);
}
