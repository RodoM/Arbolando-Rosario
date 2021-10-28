
package com.grupo9.ArbolandoRosario.Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import java.io.Serializable;


@Data
@Entity
public class Articulo {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticulo;

    private String url_imagen;
    private Arbol arbol;
    private Usuario usuario;

}
