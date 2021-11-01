package com.grupo9.ArbolandoRosario.Controladores;
import java.util.List;

import com.grupo9.ArbolandoRosario.Entidades.Articulo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.grupo9.ArbolandoRosario.Servicios.ArticuloServicio;

@Controller
public class ExplorarControlador {
    @Autowired
    private ArticuloServicio articuloServicio;
    @GetMapping("/explorar")
    public String inicio(Model model){
        //Por ahora se agregan la lista con todos los articulos
        //Luego ver tema de paginación
        List<Articulo>articulos = articuloServicio.listarArticulos();
        model.addAttribute("articulos", articulos);
        return "Explorar";
    }
}
