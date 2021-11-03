package com.grupo9.ArbolandoRosario.Controladores;
import java.util.List;

import com.grupo9.ArbolandoRosario.Entidades.Articulo;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.grupo9.ArbolandoRosario.Servicios.ArticuloServicio;
import org.springframework.data.domain.Page;
@Controller
public class ExplorarControlador {
    @Autowired
    private ArticuloServicio articuloServicio;
    @GetMapping("/explorar")
    public String inicio(@RequestParam Map<String, Object> params, Model model){
        int page = params.get("page") !=null ? Integer.valueOf(params.get("page").toString())-1 : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Articulo> pageArticulo = articuloServicio.listarPaginas(pageRequest);
        int totalPages = pageArticulo.getTotalPages();
        if(totalPages>0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("paginas", pages);
        }
        model.addAttribute("articulos", pageArticulo.getContent());
        model.addAttribute("currentPage", page+1);
        model.addAttribute("next", page+2);
        model.addAttribute("prev", page);
        return "Explorar";
    }
}
