package com.grupo9.ArbolandoRosario.Servicios;

import com.grupo9.ArbolandoRosario.Entidades.Arbol;
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
    public void guardar(Arbol arbol) {
        arbolDAO.save(arbol);
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
