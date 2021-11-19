package com.grupo9.ArbolandoRosario.Controladores;

import com.grupo9.ArbolandoRosario.Servicios.UsuarioServicio;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfiguracionControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/configuracion")
    public String configuracion() {
        return "Ajustes";
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/cambiar-clave")
    public String cambiarClave(@RequestParam String password, @RequestParam String newPassword, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            usuarioServicio.cambiarContraseña(usuarioServicio.encontrarUsuarioPorMail(auth.getName()), password, newPassword);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "Ajustes";
        }
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/cambiar-mail")
    public String cambiarMail(@RequestParam String nuevoMail) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            usuarioServicio.cambiarMail(usuarioServicio.encontrarUsuarioPorMail(auth.getName()), nuevoMail);
        } catch (Exception e) {
            System.out.println("error");
        }
        return "redirect:/logout";
    }

}
