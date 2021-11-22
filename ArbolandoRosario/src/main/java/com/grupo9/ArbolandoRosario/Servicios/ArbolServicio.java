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
        arbolDAO.delete(arbol);
    }
    
    @Transactional(readOnly = true)
    public Arbol encontrarArbolPorId(Long id) {
        Arbol resultado = arbolDAO.findById(id).orElse(null);
        return resultado;
    }
    
}
