package com.grupo9.ArbolandoRosario.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

    Usuario findByMailIgnoreCase(String mail);

    @Modifying
    @Query("update Usuario u set u.mail = ?1  where u.id = ?2")
    void setUsuarioMailByIdUsuario(String mail, Long userId);
}
