package com.grupo9.ArbolandoRosario.Servicios;

import com.grupo9.ArbolandoRosario.Repositorio.UsuarioDAO;
import com.grupo9.ArbolandoRosario.enums.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;
import com.grupo9.ArbolandoRosario.Errores.ErrorServicio;
import java.util.ArrayList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDao;

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAll();
    }

    @Transactional
    public void guardar(Usuario usuario) throws ErrorServicio {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (usuario.getMail().isEmpty() || usuario.getMail() == null) {
            throw new ErrorServicio("El correo no puede estar vacio");
        }

        if (encontrarMailYaRegistrado(usuario.getMail())) {
            throw new ErrorServicio("El email ya se encuentra registrado");
        }

        if (usuario.getContrasenha().isEmpty() || usuario.getContrasenha() == null || usuario.getContrasenha().length() < 6) {
            throw new ErrorServicio("La contraseña no puede tener menos de 6 caracteres");
        }

        usuario.setContrasenha(encoder.encode(usuario.getContrasenha()));
        usuario.setAlta(true);
        usuario.setRol(Rol.USER);
        usuario.setAvatar(elegirAvatarRandom());
        usuarioDao.save(usuario);
    }

    public String elegirAvatarRandom(){
        int cantAvatares = 12;
        int valorEntero = (int) Math.random()*(cantAvatares-1+1)+1;
        String valorAGuardar = "profile-"+valorEntero+".png";
        return valorAGuardar;
    }

    public void ValidacionesAvatarYAgregarAlModelo(Model model){
        String email;
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            email = authentication.getName();
        }catch(Exception ex){
            email = null;
        }
        Usuario user;
        if(email!=null){
            user = encontrarUsuarioPorMail(email);
        }else{
            user = null;
        }
        String porDefault = "/img/profilepicture.png";
        if(user == null){
            model.addAttribute("avatar", porDefault);
        }else{
            if(user.getAvatar() == null){
                model.addAttribute("avatar", porDefault);
            }else{
                String avatarAAgregar = "/img/profile/"+user.getAvatar();
                model.addAttribute("avatar", avatarAAgregar);
            }
        }
    }

    @Transactional
    public void eliminar(Usuario usuario) {
        usuario.setAlta(false);
        usuarioDao.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario encontrarUsuarioPorId(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    public Usuario encontrarUsuarioPorMail(String mail) {
        return usuarioDao.findByMailIgnoreCase(mail);
    }

    public boolean encontrarMailYaRegistrado(String mail) {
        return usuarioDao.findByMailIgnoreCase(mail) != null;
    }

    @Override   //este metodo lo implementa la interfaz, y se llama cuando un usuario se quiere loguear (como es un override se tiene q llamar asi, aunq nosotros autentiquemos por mail
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        try {
            Usuario usuario = usuarioDao.findByMailIgnoreCase(mail);
            User user;
            List<GrantedAuthority> permisos = new ArrayList<>();
            permisos.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            return new User(mail, usuario.getContrasenha(), permisos);
        } catch (Exception e) {
            throw new UsernameNotFoundException("No existe ningun usuario registrado bajo ese email");
        }
    }
}
