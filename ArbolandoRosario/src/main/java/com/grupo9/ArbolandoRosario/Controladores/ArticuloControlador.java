
package com.grupo9.ArbolandoRosario.Controladores;
import com.grupo9.ArbolandoRosario.Entidades.Articulo;
import com.grupo9.ArbolandoRosario.Servicios.ArticuloServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ArticuloControlador {
    
    @Autowired
    private ArticuloServicio articuloServicio;
    
    @GetMapping("/articulo")
    public String articulo (Model model) {
        return "Ver_articulo";
    }
    
    @GetMapping("/crear-articulo")
    public String crear_articulo (Model model) {
        return "Crear-articulo";
    }
 
    @GetMapping("/articulo/{idAutor}")
    public String buscarArticulo(Articulo articulo, Model model){
        articulo = articuloServicio.encontrarArticuloPorId(articulo.getIdArticulo());
        model.addAttribute("articulo", articulo);
        return "Ver_articulo";
    }

}
