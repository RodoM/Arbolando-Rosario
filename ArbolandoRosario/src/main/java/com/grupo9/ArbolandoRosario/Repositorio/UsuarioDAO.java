package com.grupo9.ArbolandoRosario.Repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long>{

}
