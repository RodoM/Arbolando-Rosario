package com.grupo9.ArbolandoRosario.Servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo9.ArbolandoRosario.Repositorio.ArticuloDAO;
import org.springframework.transaction.annotation.Transactional;
import com.grupo9.ArbolandoRosario.Entidades.Articulo;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ArticuloServicio {
    @Autowired
    private ArticuloDAO articuloDAO;

    @Transactional(readOnly = true)
    public List<Articulo> listarArticulos(){
        return articuloDAO.findAll();
    }
    @Transactional
    public void guardar(Articulo articulo){
        articuloDAO.save(articulo);
    }
    @Transactional
    public void eliminar(Articulo articulo){
        articulo.setAlta(false);
        articuloDAO.save(articulo);
    }
    @Transactional(readOnly = true)
    public Articulo encontrarArticuloPorId(Long id){
        Articulo resultado = articuloDAO.findById(id).orElse(null);
        return resultado;
    }
    public Page<Articulo> listarPaginas(Pageable pageable) {
        return articuloDAO.findAll(pageable);
    }
}
