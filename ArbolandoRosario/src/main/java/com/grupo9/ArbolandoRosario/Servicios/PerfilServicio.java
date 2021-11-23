package com.grupo9.ArbolandoRosario.Servicios;

import com.grupo9.ArbolandoRosario.Entidades.Perfil;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;
import com.grupo9.ArbolandoRosario.Repositorio.PerfilDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilServicio {

    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    PerfilDAO perfilDAO;

    @Transactional
    public void guardar(Perfil perfil, String emailOfUser) {
        Usuario user = usuarioServicio.encontrarUsuarioPorMail(emailOfUser);
        perfil.setUsuario(user);
        perfilDAO.save(perfil);
    }

    @Transactional
    public Perfil guardarVacio(String emailOfUser) {
        Usuario user = usuarioServicio.encontrarUsuarioPorMail(emailOfUser);
        Perfil perfil = new Perfil();
        perfil.setUsuario(user);
        perfil.setNombre(null);
        perfil.setInformacion(null);
        perfil.setTelefono(null);
        perfil.setZona(null);
        perfilDAO.save(perfil);
        return perfil;
    }

    public Perfil encontrarPerfilPorMail(String emailOfUser) {
        return perfilDAO.findByUsuarioMail(emailOfUser);
    }

    public Perfil encontrarPerfilPorIdUsuario(Long idUsuario) {
        return perfilDAO.findByUsuarioIdUsuario(idUsuario);
    }
}
