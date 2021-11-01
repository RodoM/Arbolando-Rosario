
package com.grupo9.ArbolandoRosario.Controladores;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ArticuloControlador {
    
    @GetMapping("/articulo")
    public String articulo (Model model) {
        return "Ver_articulo";
    }
    
    @GetMapping("/crear-articulo")
    public String crear_articulo (Model model) {
        return "Crear-articulo";
    }
    
}
