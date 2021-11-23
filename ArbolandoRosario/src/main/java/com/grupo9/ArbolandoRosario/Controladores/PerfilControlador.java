package com.grupo9.ArbolandoRosario.Controladores;

import com.grupo9.ArbolandoRosario.Servicios.ArticuloServicio;
import com.grupo9.ArbolandoRosario.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.grupo9.ArbolandoRosario.Entidades.Articulo;
import com.grupo9.ArbolandoRosario.Entidades.Perfil;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;
import com.grupo9.ArbolandoRosario.Servicios.PerfilServicio;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PerfilControlador {

    @Autowired
    UsuarioServicio usuarioServicio;
    @Autowired
    ArticuloServicio articuloServicio;
    @Autowired
    PerfilServicio perfilServicio;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(Model model) {
        usuarioServicio.ValidacionesAvatarYAgregarAlModelo(model);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = usuarioServicio.encontrarUsuarioPorMail(auth.getName());
        List<Articulo> articulosAsociados = articuloServicio.buscarPorUsuario(user);
        model.addAttribute("cant", articulosAsociados.size());
        model.addAttribute("usuario", user);
        model.addAttribute("articulos", articulosAsociados);
        Perfil perfil = perfilServicio.encontrarPerfilPorMail(auth.getName());
        if (perfil != null) {
            model.addAttribute("perfil", perfil);
        } else {
            Perfil perfilVacio = perfilServicio.guardarVacio(auth.getName());
            model.addAttribute("perfil", perfilVacio);
        }
        return "Perfil";
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/configurar-perfil")
    public String configuraPerfil(Perfil perfil, Model model) {
        usuarioServicio.ValidacionesAvatarYAgregarAlModelo(model);
        return "Editar-perfil";
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/configurar-perfil")
    public String configurarPerfil(Perfil perfil) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        perfilServicio.guardar(perfil, authentication.getName());
        return "Perfil";
    }

}
