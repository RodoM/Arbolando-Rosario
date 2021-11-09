package com.grupo9.ArbolandoRosario.Entidades;

import com.grupo9.ArbolandoRosario.enums.Rol;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private boolean alta;
    private String avatar;
    private String mail;
    private String contrasenha;
}
