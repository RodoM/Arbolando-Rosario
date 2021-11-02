package com.grupo9.ArbolandoRosario.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "arbol")
public class Arbol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArbol;

    private String nombre;
    private String nombreCientifico;
    private boolean alta;

    @OneToOne
    private TipoArbol tipoArbol;

    private String origen;
    private String tamanho;
    private String informacion;

}
