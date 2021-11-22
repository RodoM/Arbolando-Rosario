package com.grupo9.ArbolandoRosario.Servicios;

import com.grupo9.ArbolandoRosario.Entidades.Arbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo9.ArbolandoRosario.Repositorio.ArticuloDAO;
import org.springframework.transaction.annotation.Transactional;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import com.grupo9.ArbolandoRosario.Entidades.Articulo;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;
import com.grupo9.ArbolandoRosario.Errores.ErrorServicio;
import com.grupo9.ArbolandoRosario.Repositorio.ArbolDAO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ArticuloServicio {

    @Autowired
    private ArticuloDAO articuloDAO;
    @Autowired
    private ArbolDAO arbolDAO;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Transactional(readOnly = true)
    public List<Articulo> listarArticulos() {
        return articuloDAO.findAll();
    }

    @Transactional
    public void guardar(Articulo articulo, String emailOfUser) throws ErrorServicio {
        Usuario user = usuarioServicio.encontrarUsuarioPorMail(emailOfUser);
        articulo.setUsuario(user);
        if (articulo.getUrl_imagen().isEmpty() || articulo.getUrl_imagen() == null) {
            throw new ErrorServicio("La imagen no puede estar vacia");
        }
        if (articulo.getArbol() == null) {
            throw new ErrorServicio("El articulo debe estar asociado a un arbol");
        }
        if (articulo.getUsuario() == null) {
            throw new ErrorServicio("El articulo debe estar asociado a un usuario");
        }
        validarArbol(articulo.getArbol());

        articulo.setAlta(true);
        articuloDAO.save(articulo);
    }

    public void guardarParaBorrar(Articulo articulo) {
        articuloDAO.save(articulo);
    }

    @Transactional
    public void eliminar(Articulo articulo, Arbol arbol) {
        System.out.println(arbol.getNombre());
        articuloDAO.delete(articulo);
        arbolDAO.delete(arbol);
    }

    public List<Articulo> buscarPorUsuario(Usuario user) {
        try {
            return (List<Articulo>) articuloDAO.findByUsuarioMail(user.getMail());
        } catch (Exception ex) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Articulo encontrarArticuloPorId(Long id) {
        return articuloDAO.findById(id).orElse(null);
    }

    public Page<Articulo> listarPaginas(Pageable pageable) {
        return articuloDAO.findAll(pageable);
    }

    public boolean encontrarArbolRepetidoNombre(String nombre) {
        return arbolDAO.findByNombreIgnoreCase(nombre) != null;
    }

    public boolean encontrarArbolRepetidoNombreCientifico(String nombreCientifico) {
        return arbolDAO.findByNombreCientificoIgnoreCase(nombreCientifico) != null;
    }

    public void validarArbol(Arbol arbol) throws ErrorServicio {
        if (arbol.getNombre().isEmpty() || arbol.getNombre() == null) {
            throw new ErrorServicio("El nombre no puede estar vacio");
        }
        if (arbol.getNombreCientifico().isEmpty() || arbol.getNombreCientifico() == null) {
            throw new ErrorServicio("El nombre cientifico no puede estar vacio");
        }
        if (arbol.getOrigen().isEmpty() || arbol.getOrigen() == null) {
            throw new ErrorServicio("El origen del arbol no puede estar vacio");
        }
        if (arbol.getTamanho().isEmpty() || arbol.getTamanho() == null) {
            throw new ErrorServicio("El tamaño del arbol no puede estar vacio");
        }
        if (arbol.getTipoArbol().getNombre().isEmpty() || arbol.getTipoArbol().getNombre() == null) {
            throw new ErrorServicio("El tipo de arbol no puede estar vacio");
        }
        if (encontrarArbolRepetidoNombre(arbol.getNombre())) {
            throw new ErrorServicio("Ya existe un arbol registrado bajo ese nombre");
        }
        if (encontrarArbolRepetidoNombreCientifico(arbol.getNombreCientifico())) {
            throw new ErrorServicio("Ya existe un arbol registrado bajo ese nombre cientifico");
        }
        if (arbol.getInformacion().length() > 3000) {
            throw new ErrorServicio("La informacion no puede sobrepasar los 3000 caracteres");
        }
    }
}
