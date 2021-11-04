package com.grupo9.ArbolandoRosario.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroInicioSesionControlador {

    @GetMapping("/login")
    public String login(Model model) {
        return "Incio-Sesion";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        return "Registro";
    }
}
