package com.grupo9.ArbolandoRosario.Repositorio;

import com.grupo9.ArbolandoRosario.Entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilDAO extends JpaRepository<Perfil, Long> {

    Perfil findByUsuarioMail(String mail);
}
