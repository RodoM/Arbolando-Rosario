package com.grupo9.ArbolandoRosario.Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import lombok.Data;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;

@Data
@Entity
@Table(name = "articulo")
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticulo;
    @Column(length=4000)
    private String url_imagen;
    private boolean alta;
    @OneToOne(cascade = {CascadeType.ALL})
    private Arbol arbol;
    @OneToOne(cascade = {CascadeType.ALL})
    private Usuario usuario;
}
