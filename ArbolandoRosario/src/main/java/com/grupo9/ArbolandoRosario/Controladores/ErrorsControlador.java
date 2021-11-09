
package com.grupo9.ArbolandoRosario.Controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorsControlador implements ErrorController {
    @RequestMapping(value = "/error", method = {RequestMethod.GET,RequestMethod.POST})
    public String mostrarPaginaDeError(Model model,HttpServletRequest httpServletRequest){
        String mensajeError = "";
        int codigoError = (int) httpServletRequest.getAttribute("javax.servlet.error.status_code");
        switch (codigoError) {
            case 400:
                mensajeError = "El recurso Solicitado No Existe";
                    break;
            case 401:
                mensajeError = "El Usuario No se Encuentra Actualizado"; //es porque se necesita iniciar sesion
                break;
            case 403:
                mensajeError = "El Usuario no tiene permizos para acceder al recurso"; //es por el Rol
                break;
            case 404:
                mensajeError = "El recurso solicitado no se ha encontrado"; // el mas recurrente al escribir mal una url
                break;
            case 500:
                mensajeError = "El servidor no pudo realizar la accion con exito";
                break;
            default:
        }// los 400 son errores de cliente. los 500 son de servidor
        model.addAttribute("codigo", codigoError);
        model.addAttribute("mensaje", mensajeError);
            return "/error";
}
}