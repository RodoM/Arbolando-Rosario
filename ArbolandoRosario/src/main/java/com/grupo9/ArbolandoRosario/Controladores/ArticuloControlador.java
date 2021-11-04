
package com.grupo9.ArbolandoRosario.Controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.grupo9.ArbolandoRosario.Entidades.Articulo;
import com.grupo9.ArbolandoRosario.Servicios.ArticuloServicio;

@Controller
public class ArticuloControlador {
    @Autowired
    ArticuloServicio articuloServicio;
    @GetMapping("/articulo")
    public String articulo (Model model) {
        return "Ver_articulo";
    }
    
    @GetMapping("/crear-articulo")
    public String crear_articulo (Articulo articulo) {
        return "Crear-articulo";
    }
    @PostMapping("/crear-articulo")
    public String guardarArticulo(Model model, Articulo articulo){
        articuloServicio.guardar(articulo);
        return "redirect:/Index";
    }
    
}
