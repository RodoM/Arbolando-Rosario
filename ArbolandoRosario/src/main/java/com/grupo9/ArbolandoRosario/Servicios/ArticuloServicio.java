package com.grupo9.ArbolandoRosario.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo9.ArbolandoRosario.Repositorio.ArticuloDAO;
import org.springframework.transaction.annotation.Transactional;
import com.grupo9.ArbolandoRosario.Entidades.Articulo;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;
import com.grupo9.ArbolandoRosario.Errores.ErrorServicio;
import java.util.List;
import com.grupo9.ArbolandoRosario.Servicios.UsuarioServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ArticuloServicio {
    
    @Autowired
    private ArticuloDAO articuloDAO;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Transactional(readOnly = true)
    public List<Articulo> listarArticulos() {
        return articuloDAO.findAll();
    }
    
    @Transactional
    public void guardar(Articulo articulo, String emailOfUser) throws ErrorServicio {
        Usuario user = usuarioServicio.loadUserByUsername(mail);
        if (articulo.getUrl_imagen().isEmpty() || articulo.getUrl_imagen() == null) {
            throw new ErrorServicio("La imagen no puede estar vacia");
        }
        if (articulo.getArbol() == null) {
            throw new ErrorServicio("El articulo debe estar asociado a un arbol");
        }
        if (articulo.getUsuario() == null) {
            throw new ErrorServicio("El articulo debe estar asociado a un usuario");
        }
        articulo.setAlta(true);
        articuloDAO.save(articulo);
    }
    
    @Transactional
    public void eliminar(Articulo articulo) {
        articulo.setAlta(false);
        articuloDAO.save(articulo);
    }
    
    @Transactional(readOnly = true)
    public Articulo encontrarArticuloPorId(Long id) {
        Articulo resultado = articuloDAO.findById(id).orElse(null);
        return resultado;
    }
    
    public Page<Articulo> listarPaginas(Pageable pageable) {
        return articuloDAO.findAll(pageable);
    }
}
