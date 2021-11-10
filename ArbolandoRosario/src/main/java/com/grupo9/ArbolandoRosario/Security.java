package com.grupo9.ArbolandoRosario;

import com.grupo9.ArbolandoRosario.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //esto autoriza las url para q puedan acceder solo los roles permitidos
public class Security extends WebSecurityConfigurerAdapter { //le indica a spring security q esta clase de ocupa de la seguridad

    @Autowired
    private UsuarioServicio usuarioServicio;

    //metodo q va a configurar la autenticacion
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
    }

    //configuracion peticiones http
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/*", "/img/*", "/js/*").permitAll()
                .and().formLogin().loginPage("/login").usernameParameter("mail") //el usernameparameter es el name q tienen q usar SIOSI los de front
                .passwordParameter("contrasenha") // y lo mismo para la contraseña
                .defaultSuccessUrl("/?login") // aca se pone la pagina donde se envia al user desp de loguearse correctamente
                .loginProcessingUrl("/logincheck") //esta es x defecto de spring security
                .failureUrl("/login?error=error")
                .permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/?logout")
                .and().csrf().disable();
    }

}
