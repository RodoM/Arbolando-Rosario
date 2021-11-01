package com.grupo9.ArbolandoRosario.Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import lombok.Data;
import java.io.Serializable;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "articulo")
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticulo;
    private String url_imagen;

    @OneToOne
    private Arbol arbol;
    @OneToOne
    private Usuario usuario;
}
