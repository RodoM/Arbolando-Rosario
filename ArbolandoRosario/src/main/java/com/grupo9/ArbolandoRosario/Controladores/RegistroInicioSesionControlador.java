package com.grupo9.ArbolandoRosario.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.grupo9.ArbolandoRosario.Entidades.Usuario;
import com.grupo9.ArbolandoRosario.Errores.ErrorServicio;
import com.grupo9.ArbolandoRosario.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class RegistroInicioSesionControlador {
    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/login")
    public String login(Model model, Usuario usuario) {
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
            model.addAttribute("Error", e.getMessage());
            return "Registro";
        }
    }
    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("success", "El usuario se ha deslogeado correctamente");
        return "redirect:/";
    }
}
