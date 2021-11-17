package com.grupo9.ArbolandoRosario.Controladores;

import com.grupo9.ArbolandoRosario.Entidades.Articulo;
import com.grupo9.ArbolandoRosario.Errores.ErrorServicio;
import com.grupo9.ArbolandoRosario.Servicios.ArticuloServicio;
import com.grupo9.ArbolandoRosario.Servicios.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class ArticuloControlador {

    @Autowired
    private ArticuloServicio articuloServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @GetMapping("/articulo")
    public String articulo(Model model) {
        usuarioServicio.ValidacionesAvatarYAgregarAlModelo(model);
        return "Ver-articulo";
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/crear-articulo")
    public String crear_articulo(Articulo articulo) {
        usuarioServicio.ValidacionesAvatarYAgregarAlModelo(model);
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
            return "redirect:/crear-articulo";
        }
        redirectAttributes.addFlashAttribute("success", "Se ha creado el articulo con éxito");
        return "redirect:/";
    }

    @GetMapping("/mostrar")
    public String buscarArticulo(@RequestParam Map<String, Object> params, Model model, Articulo articulo) {
        usuarioServicio.ValidacionesAvatarYAgregarAlModelo(model);
        int idArticulo = params.get("id") !=null ? Integer.valueOf(params.get("id").toString()) : 0;
        Long id = Long.valueOf(idArticulo);
        model.addAttribute("articulo", articuloServicio.encontrarArticuloPorId(id));
        return "Ver-articulo";
    }

}
