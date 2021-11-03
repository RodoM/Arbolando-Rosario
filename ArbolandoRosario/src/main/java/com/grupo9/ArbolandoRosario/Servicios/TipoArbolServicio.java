package com.grupo9.ArbolandoRosario.Servicios;

import com.grupo9.ArbolandoRosario.Entidades.TipoArbol;
import com.grupo9.ArbolandoRosario.Repositorio.TipoArbolDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoArbolServicio {

    @Autowired
    private TipoArbolDAO tipoArbolDAO;

    @Transactional(readOnly = true)
    public List<TipoArbol> listarTipoArbol() {
        return tipoArbolDAO.findAll();
    }

    @Transactional
    public void guardar(TipoArbol tipoArbol) {
        tipoArbolDAO.save(tipoArbol);
    }

    @Transactional
    public void eliminar(TipoArbol tipoArbol) {
        tipoArbolDAO.delete(tipoArbol);
    }

    @Transactional(readOnly = true)
    public TipoArbol encontrarTipoArbolPorId(Long id) {
        TipoArbol resultado = tipoArbolDAO.findById(id).orElse(null);
        return resultado;
    }
}
