package com.grupo9.ArbolandoRosario.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.Column;

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

    @OneToOne(cascade = {CascadeType.ALL})
    private TipoArbol tipoArbol;
    
    private String origen;
    private String tamanho;
    @Column(length=4000)
    private String informacion;

}
