package com.grupo9.ArbolandoRosario.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.grupo9.ArbolandoRosario.Servicios.UsuarioServicio;

@Controller
public class IndexControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String inicio(Model model, @RequestParam(required = false) String login, @RequestParam(required = false) String logout) {
        usuarioServicio.ValidacionesAvatarYAgregarAlModelo(model);
        return "Index";
    }

    @GetMapping("/privacidad")
    public String politicaPrivacidad(Model model) {
        usuarioServicio.ValidacionesAvatarYAgregarAlModelo(model);
        return "Privacidad";
    }
}
