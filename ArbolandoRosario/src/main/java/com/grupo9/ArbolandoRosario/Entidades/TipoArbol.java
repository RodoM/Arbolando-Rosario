package com.grupo9.ArbolandoRosario.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_arbol")
public class TipoArbol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoArbol;
    private boolean alta;
    private String nombre;
}
