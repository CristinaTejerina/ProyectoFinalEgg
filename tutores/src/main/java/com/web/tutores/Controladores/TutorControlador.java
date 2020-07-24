package com.web.tutores.Controladores;

import com.web.tutores.Entidades.Materia;
import com.web.tutores.Entidades.Tutor;
import com.web.tutores.Entidades.Usuario;
import com.web.tutores.Entidades.Zona;
<<<<<<< HEAD
import com.web.tutores.Repositorios.MateriaRepositorio;

import com.web.tutores.Repositorios.UsuarioRepositorio;

=======
import com.web.tutores.Errores.ErrorServicio;
import com.web.tutores.Repositorios.MateriaRepositorio;
>>>>>>> ed23c44496fed4d0f69200fd2275a332cd28ed9e
import com.web.tutores.Repositorios.ZonaRepositorio;
import com.web.tutores.Servicio.TutorServicio;
import com.web.tutores.Servicio.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tutor")
public class TutorControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ZonaRepositorio zonaRepositorio;

    @Autowired
    private MateriaRepositorio materiaRepositorio;

    @Autowired
    private TutorServicio tutorServicio;

    public Usuario usuarioLogueado() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioServicio.buscarPorEmail(auth.getName());

        return usuario;
    }

    @PreAuthorize("hasAnyRole('ROLE_TUTOR')")
    @GetMapping("/iniciotutor")
    public String inicioTutor(HttpSession session) {

        session.setAttribute("clientesession", usuarioLogueado());
        return "inicioTutor.html";
    }

    @GetMapping("/registroTutor")
    public String registro(ModelMap modelo) {
        List<Zona> zonas = zonaRepositorio.findAll();
        modelo.put("zonas", zonas);
        List<Materia> materias = materiaRepositorio.findAll();
        modelo.put("materias", materias);

        return "registroTutor.html";
    }

    @GetMapping("/editar-tutor")
    public String modificar(ModelMap modelo) {
        List<Zona> zonas = zonaRepositorio.findAll();
        modelo.put("zonas", zonas);
        return "configuracionTutor.html";
    }

    @GetMapping("/deshabilitar")
    public String deshabilitar(ModelMap modelo) {
        List<Zona> zonas = zonaRepositorio.findAll();
        modelo.put("zonas", zonas);
        return "deshabilitar.html";
    }

    @GetMapping("/habilitar")
    public String habilitar(ModelMap modelo) {
        List<Zona> zonas = zonaRepositorio.findAll();
        modelo.put("zonas", zonas);
        return "habilitar.html";
    }

    
    @PostMapping("/registrarTutor")
    public String registrar(ModelMap modelo, MultipartFile archivo,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String mail,
            @RequestParam String clave,
<<<<<<< HEAD
            @RequestParam String clave2, @RequestParam String telefono, String idZona) {

//        try {
//            tutorServicio.crearTutor(archivo, nombre, apellido, mail, clave, clave2, telefono, idZona, materia, descripcion);
//
//            archivo, String nombre, String apellido, String mail, String clave, String telefono, String idZona, Foto foto, List<Materia> materias, String descripcion
//        } catch (ErrorServicio ex) {
//
//            List<Zona> zonas = zonaRepositorio.findAll();
//            modelo.put("zonas", zonas);
//            modelo.put("error", ex.getMessage());
//            modelo.put("nombre", nombre);
//            modelo.put("apellido", apellido);
//            modelo.put("mail", mail);
//            modelo.put("clave", clave);
//            modelo.put("clave2", clave2);
//            modelo.put("telefono", telefono);
//
//            return "registro2.html";
//        }
//        modelo.put("titulo", "¡Bienvenido a la comunidad de Tutores.com !");
//        modelo.put("descripcion", "Tu usuario fue registrado correctamene, ¡¡Bienvenido!!");
//        return "exito.html";
        return "inicioTutor.html";

=======
            @RequestParam String clave2, @RequestParam String telefono, @RequestParam String descripcion, String idZona, String idMateria) {

        try {
            tutorServicio.crearTutor(archivo, nombre, apellido, mail, clave, clave2, telefono, idZona, idMateria, descripcion);
        } catch (ErrorServicio ex) {

            List<Zona> zonas = zonaRepositorio.findAll();
            modelo.put("zonas", zonas);
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);
            modelo.put("clave", clave);
            modelo.put("clave2", clave2);
            modelo.put("telefono", telefono);
            List<Materia> materias = materiaRepositorio.findAll();
            modelo.put("materias", materias);
            modelo.put("descripcion", descripcion);

            return "registroTutor.html";
        }
        
        modelo.put("titulo", "¡Bienvenido a la comunidad de Tutores.com !");
        modelo.put("descripcion", "Te has registrado correctamene como Tutor, ¡¡Bienvenido!!");
        return "exito.html";
>>>>>>> ed23c44496fed4d0f69200fd2275a332cd28ed9e
    }

    @GetMapping("/listado")
    public ModelAndView listar(@RequestParam(required = false) String q) {

        ModelAndView modelV = new ModelAndView("nombrevista");

        List<Tutor> tutores;    

        if (q == null || q.isEmpty()) {
            tutores = tutorServicio.listarActivos();
        } else {
            tutores = tutorServicio.listarActivos(q);
        }

        modelV.addObject("listatutores", tutores);

        return modelV;
    }

}
