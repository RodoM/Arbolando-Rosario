package com.grupo9.ArbolandoRosario.Entidades;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sesion")
public class Sesion implements Serializable{
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSesion;
    private String mail;
    private String contrasenha;
}
