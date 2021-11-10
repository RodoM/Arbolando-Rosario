package com.grupo9.ArbolandoRosario.Controladores;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexControlador {
    @GetMapping("/")
    public String inicio(Model model, @RequestParam(required = false) String login, @RequestParam(required = false) String logout){
        return "Index";
    }
}
