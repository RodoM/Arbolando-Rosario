package com.grupo9.ArbolandoRosario.Controladores;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlador {
    @GetMapping("/")
    public String inicio(Model model){
        return "Index";
    }
}
