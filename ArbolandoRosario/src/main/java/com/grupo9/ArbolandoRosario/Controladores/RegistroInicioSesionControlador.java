package com.grupo9.ArbolandoRosario.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;
import com.grupo9.ArbolandoRosario.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class RegistroInicioSesionControlador {
    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/login")
    public String login(Model model, Usuario usuario) {
        return "Inicio-Sesion";
    }
    @GetMapping("/registro")
    public String registro(Model model, Usuario usuario) {
        return "Registro";
    }
    @PostMapping("/registro")
    public String registroSend(Model model, Usuario usuario) {
        usuarioServicio.guardar(usuario);
        return "redirect:/Index";
    }
    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/";
    }
}
