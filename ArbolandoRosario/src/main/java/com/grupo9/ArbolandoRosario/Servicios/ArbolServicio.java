package com.grupo9.ArbolandoRosario.Servicios;

import com.grupo9.ArbolandoRosario.Repositorio.ArbolDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArbolServicio {

    @Autowired
    private ArbolDAO arbolDAO;

}
