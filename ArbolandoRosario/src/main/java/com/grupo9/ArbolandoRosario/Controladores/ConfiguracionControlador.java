package com.grupo9.ArbolandoRosario.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfiguracionControlador {

    @GetMapping("/configuracion")
    public String configuracion() {
        return "Index";
    }
}
