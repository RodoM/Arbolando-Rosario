package com.grupo9.ArbolandoRosario.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;
import com.grupo9.ArbolandoRosario.Errores.ErrorServicio;
import com.grupo9.ArbolandoRosario.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroInicioSesionControlador {
    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/login")
    public String login(Model model, Usuario usuario, @RequestParam(required = false) String error) {
        if (error != null){
            model.addAttribute("error", "El usuario ingresado o la contraseña son incorrectos");
        }
        return "Inicio-Sesion";
    }
    @GetMapping("/registro")
    public String registro(Model model, Usuario usuario) {
        return "Registro";
    }
    @PostMapping("/registro")
    public String registroSend(Model model, Usuario usuario) {
        try{
            usuarioServicio.guardar(usuario);
            return "redirect:/";
        }catch(ErrorServicio e){
            model.addAttribute("error", e.getMessage());
            return "Registro";
        }
    }
}
