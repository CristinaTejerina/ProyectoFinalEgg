package com.web.tutores.Controladores;

import com.web.tutores.Entidades.Usuario;
import com.web.tutores.Entidades.Zona;
import com.web.tutores.Errores.ErrorServicio;
import com.web.tutores.Repositorios.ZonaRepositorio;
import com.web.tutores.Servicio.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionIdListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PlataformaControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ZonaRepositorio zonaRepositorio;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o Clave incorrecto.");
        }
        if (logout != null) {
            model.put("logout", "Ha cerrado sesion correctamente.");

        }
        return "login.html";
    }

    @GetMapping("/registro")
    public String registro(ModelMap modelo) {
        List<Zona> zonas = zonaRepositorio.findAll();
        modelo.put("zonas", zonas);
        return "registro2.html";
    }

    public Usuario usuarioLogueado() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Usuario usuario = usuarioServicio.buscarPorEmail(auth.getName());

        return usuario;
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("/inicio")
    public String inicio(ModelMap model, HttpSession session) {

        session.setAttribute("clientesession", usuarioLogueado());

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

//    @PostMapping("/registrar")
//    public String registrar(ModelMap modelo, MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2, String idZona) {
//        try {
//            usuarioServicio.registrar(archivo, nombre, apellido, mail, clave1, clave2, idZona);
//        } catch (ErrorServicio ex) {
//
//            List<Zona> zonas = zonaRepositorio.findAll();
//            modelo.put("zonas", zonas);
//            modelo.put("error", ex.getMessage());
//            modelo.put("nombre", nombre);
//            modelo.put("apellido", apellido);
//            modelo.put("maiil", mail);
//            modelo.put("clave1", clave1);
//            modelo.put("clave2", clave2);
//
//            return "registro.html";
//        }
//        modelo.put("titulo", "¡Bienvenido a la comunidad de Tinder !");
//        modelo.put("descripcion", "Tu usuario fue registrado correctamene, ¡¡Bienvenido!!");
//        return "exito.html";
//    }
}
