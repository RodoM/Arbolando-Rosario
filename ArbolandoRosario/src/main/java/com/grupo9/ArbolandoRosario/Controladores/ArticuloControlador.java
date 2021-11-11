package com.grupo9.ArbolandoRosario.Controladores;

import com.grupo9.ArbolandoRosario.Entidades.Articulo;
import com.grupo9.ArbolandoRosario.Errores.ErrorServicio;
import com.grupo9.ArbolandoRosario.Servicios.ArticuloServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class ArticuloControlador {

    @Autowired
    private ArticuloServicio articuloServicio;

    @GetMapping("/articulo")
    public String articulo(Model model) {
        return "Ver-articulo";
    }

    @GetMapping("/crear-articulo")
    public String crear_articulo(Articulo articulo) {
        return "Crear-articulo";
    }

    @PostMapping("/crear-articulo")
    public String guardarArticulo(RedirectAttributes redirectAttributes, Articulo articulo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        try {
            articuloServicio.guardar(articulo, email);
        } catch (ErrorServicio ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/articulo/{idAutor}")
    public String buscarArticulo(Articulo articulo, Model model) {
        articulo = articuloServicio.encontrarArticuloPorId(articulo.getIdArticulo());
        model.addAttribute("articulo", articulo);
        return "Ver_articulo";
    }

}
