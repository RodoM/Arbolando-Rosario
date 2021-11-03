package com.grupo9.ArbolandoRosario;

import com.grupo9.ArbolandoRosario.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //esto autoriza las url para q puedan acceder solo los roles permitidos
public class Security extends WebSecurityConfigurerAdapter { //le indica a spring security q esta clase de ocupa de la seguridad

    @Autowired
    private UsuarioServicio usuarioServicio;
}
