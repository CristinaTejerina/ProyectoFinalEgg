
package com.web.tutores.Controladores;

import com.web.tutores.Entidades.Zona;
import com.web.tutores.Repositorios.ZonaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PlataformaControlador {
    
    
    @Autowired
    private ZonaRepositorio zonaRepositorio;
    
    
     @GetMapping("/")
    public String index() {
        return "index.html";
    }
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error,@RequestParam(required = false) String logout,ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o Clave incorrecto.");
        }
        if(logout!= null){
            model.put("logout", "Ha cerrado sesion correctamente.");
            
        }
        return "login.html";
    }
    
    @GetMapping("/registro")
    public String registro(ModelMap modelo) {
        List<Zona> zonas = zonaRepositorio.findAll();
        modelo.put("zonas", zonas);
        return "registro.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }
    
     @GetMapping("/perfil")
    public String perfil() {
        return "perfil.html";
    }
    
     @GetMapping("/exito")
    public String exito(ModelMap modelo) {
         modelo.put("titulo", "¡Bienvenido nuevamente !");
        modelo.put("descripcion", "Tu usuario fue registrado correctamene, ¡¡Bienvenido!!");
        return "exito.html";
    }
}
