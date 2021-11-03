package com.grupo9.ArbolandoRosario.Servicios;

import com.grupo9.ArbolandoRosario.Repositorio.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;
import java.util.ArrayList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDao;

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAll();
    }

    @Transactional
    public void guardar(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Transactional
    public void eliminar(Usuario usuario) {
        usuario.setAlta(false);
        usuarioDao.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario encontrarUsuarioPorId(Long id) {
        Usuario resultado = usuarioDao.findById(id).orElse(null);
        return resultado;
    }

    @Override   //este metodo lo implementa la interfaz, y se llama cuando un usuario se quiere loguear (como es un override se tiene q llamar asi, aunq nosotros autentiquemos por mail
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        try {
            Usuario usuario = usuarioDao.findByMailIgnoreCase(mail);
            User user;
            List<GrantedAuthority> permisos = new ArrayList<>();
            //aca se agregarian los permisos q va a recibir el usuario pero como esta parte tdvia n la vimos la dejo en blanco
            //permisos.add(new SimpleGrantedAuthority(ACA SE LLAMARIA EL ENUM))
            return new User(mail, usuario.getContrasenha(), permisos);
        } catch (Exception e) {
            throw new UsernameNotFoundException("No existe ningun usuario registrado bajo ese email");
        }
    }
}
