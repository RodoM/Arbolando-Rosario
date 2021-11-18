package com.grupo9.ArbolandoRosario.Controladores;

import com.grupo9.ArbolandoRosario.Servicios.UsuarioServicio;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfiguracionControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/configuracion")
    public String configuracion() {
        return "Index";
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/cambiar-clave")
    public String cambiarClave(@RequestParam Map<String, Object> params, @RequestParam String password, @RequestParam String newPassword, Model model) {
        try {
            int idUsuario = params.get("id") != null ? Integer.valueOf(params.get("id").toString()) : 0;
            Long id = Long.valueOf(idUsuario);
            usuarioServicio.cambiarContraseña(usuarioServicio.encontrarUsuarioPorId(id), password, newPassword);
        } catch (Exception e) {
            model.addAttribute("error", e);
        }

        return "Perfil";
    }
}
