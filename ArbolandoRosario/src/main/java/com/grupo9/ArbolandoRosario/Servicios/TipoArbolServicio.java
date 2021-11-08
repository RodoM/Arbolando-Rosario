package com.grupo9.ArbolandoRosario.Servicios;

import com.grupo9.ArbolandoRosario.Entidades.TipoArbol;
import com.grupo9.ArbolandoRosario.Errores.ErrorServicio;
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
    public void guardar(TipoArbol tipoArbol) throws ErrorServicio {
        if (tipoArbol.getNombre().isEmpty() || tipoArbol.getNombre() == null) {
            throw new ErrorServicio("El nombre no puede estar vacio");
        }
        tipoArbol.setAlta(true);
        tipoArbolDAO.save(tipoArbol);
    }
    
    @Transactional
    public void eliminar(TipoArbol tipoArbol) {
        tipoArbol.setAlta(false);
        tipoArbolDAO.save(tipoArbol);
    }
    
    @Transactional(readOnly = true)
    public TipoArbol encontrarTipoArbolPorId(Long id) {
        TipoArbol resultado = tipoArbolDAO.findById(id).orElse(null);
        return resultado;
    }
}
