package com.grupo9.ArbolandoRosario.Servicios;
import com.grupo9.ArbolandoRosario.Repositorio.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;

public class UsuarioServicio {
    @Autowired
    private UsuarioDAO usuarioDao;

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios(){
        return usuarioDao.findAll();
    }

    @Transactional
    public void guardar(Usuario usuario){
        usuarioDao.save(usuario);
    }

    @Transactional
    public void eliminar(Usuario usuario){
        usuarioDao.delete(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario encontrarUsuarioPorId(Long id){
        Usuario resultado = usuarioDao.findById(id).orElse(null);
        return resultado;
    }
}
