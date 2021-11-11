package com.grupo9.ArbolandoRosario.Servicios;

import com.grupo9.ArbolandoRosario.Entidades.Arbol;
import com.grupo9.ArbolandoRosario.Errores.ErrorServicio;
import com.grupo9.ArbolandoRosario.Repositorio.ArbolDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArbolServicio {

    @Autowired
    private ArbolDAO arbolDAO;

    @Transactional(readOnly = true)
    public List<Arbol> listarArboles() {
        return arbolDAO.findAll();
    }

    @Transactional
    public void guardar(Arbol arbol) throws ErrorServicio {
        if (arbol.getNombre().isEmpty() || arbol.getNombre() == null) {
            throw new ErrorServicio("El nombre del arbol no puede estar vacio");
        }
        if (arbol.getNombreCientifico().isEmpty() || arbol.getNombreCientifico() == null) {
            throw new ErrorServicio("El nombre cientifico del arbol no puede estar vacio");
        }

        if (encontrarArbolRepetidoNombre(arbol.getNombre())) {
            throw new ErrorServicio("Ya existe un arbol registrado bajo ese nombre");
        }

        if (encontrarArbolRepetidoNombreCientifico(arbol.getNombreCientifico())) {
            throw new ErrorServicio("Ya existe un arbol registrado bajo ese nombre cientifico");
        }

        if (arbol.getOrigen().isEmpty() || arbol.getOrigen() == null) {
            throw new ErrorServicio("El origen del arbol no puede estar vacio");
        }
        if (arbol.getTamanho().isEmpty() || arbol.getTamanho() == null) {
            throw new ErrorServicio("El tamaño del arbol no puede estar vacio");
        }
        if (arbol.getInformacion().length() > 700) {
            throw new ErrorServicio("La informacion no puede sobrepasar los 700 caracteres");
        }
        if (arbol.getTipoArbol() == null) {
            throw new ErrorServicio("Selecione un tipo de arbol");
        }
        arbol.setAlta(true);
        arbolDAO.save(arbol);
    }

    public boolean encontrarArbolRepetidoNombre(String nombre) {
        return arbolDAO.findByNombreIgnoreCase(nombre) != null;
    }

    public boolean encontrarArbolRepetidoNombreCientifico(String nombreCientifico) {
        return arbolDAO.findByNombreCientificoIgnoreCase(nombreCientifico) != null;
    }

    @Transactional
    public void eliminar(Arbol arbol) {
        arbol.setAlta(false);
        arbolDAO.save(arbol);
    }

    @Transactional(readOnly = true)
    public Arbol encontrarArbolPorId(Long id) {
        Arbol resultado = arbolDAO.findById(id).orElse(null);
        return resultado;
    }

}
