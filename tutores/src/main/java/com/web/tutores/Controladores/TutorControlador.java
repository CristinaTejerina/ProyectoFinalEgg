package com.web.tutores.Controladores;

import com.web.tutores.Entidades.Materia;
import com.web.tutores.Entidades.Tutor;
import com.web.tutores.Entidades.Zona;
import com.web.tutores.Errores.ErrorServicio;
import com.web.tutores.Repositorios.MateriaRepositorio;
import com.web.tutores.Repositorios.ZonaRepositorio;
import com.web.tutores.Servicio.TutorServicio;
import java.util.Date;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/tutor")
public class TutorControlador extends Controlador {

    @Autowired
    private ZonaRepositorio zonaRepositorio;

    @Autowired
    private MateriaRepositorio materiaRepositorio;

    @Autowired
    private TutorServicio tutorServicio;

    @PreAuthorize("hasAnyRole('ROLE_TUTOR')")
    @GetMapping("/inicioTutor")
    public String inicioTutor(HttpSession session) {

        session.setAttribute("clientesession", tutorLogueado());
        return "inicioTutor.html";
    }

    @GetMapping("/registroTutor")
    public String registro(ModelMap modelo) {
        List<Zona> zonas = zonaRepositorio.findAll();
        modelo.put("zonas", zonas);
        List<Materia> materias = materiaRepositorio.findAll();
        modelo.put("materias", materias);

        modelo.put("titulo", "¡Bienvenido nuevamente !");

        return "registroTutor.html";
    }

    @GetMapping("/editar-tutor")
    public String modificar(@RequestParam String id, ModelMap model) throws ErrorServicio {
        List<Zona> zonas = zonaRepositorio.findAll();
        model.put("zonas", zonas);

        List<Materia> materias = materiaRepositorio.findAll();
        model.put("materias", materias);

        Tutor tutor = tutorServicio.buscarPorId(id);
        model.addAttribute("perfil", tutor);

        return "perfilTutor4.html";
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
    public String registrar(ModelMap modelo, MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave, @RequestParam String clave2, @RequestParam String telefono, String descripcion, String idZona, String idMateria) {
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

    }

    @PostMapping("/enviarTutor")
    public String enviarTutor(@RequestParam String idTutor) { //esta bien??
        String id = idTutor;
        return id;
    }

    @GetMapping("/mostrarTutor")
    public String mostrarTutor(@RequestParam String id) {

        try {
            Tutor tutor = tutorServicio.buscarPorId(id);

            return "perfilTutor.html";
        } catch (ErrorServicio e) {
            return "error.html";

        }

    }

    @PostMapping("/actualizar-perfilTutor")
    public String actualizar(ModelMap modelo,
             @RequestParam String id, 
             @RequestParam String nombre, 
             @RequestParam String apellido, 
             @RequestParam String mail, 
             @RequestParam String clave,
             @RequestParam String clave2, 
             @RequestParam String telefono, 
             @RequestParam String descripcion, 
             @RequestParam String idZona, @RequestParam String idMateria) {
        Tutor tutor = null;

        try {
            tutor = tutorServicio.buscarPorId(id);
            tutorServicio.modificarTutor(id, nombre, apellido, mail, clave, clave2, telefono, idZona, idMateria, descripcion);

        } catch (ErrorServicio ex) {
            List<Zona> zonas = zonaRepositorio.findAll();
            modelo.put("zonas", zonas);
            List<Materia> materias = materiaRepositorio.findAll();
            modelo.put("materias", materias);
            modelo.put("error", ex.getMessage());
            modelo.put("perfil", tutor);

            return "perfilTutor4.html";
        }
        return "redirect:/tutor/inicioTutor";
    }
    
    @GetMapping("/elimina-Tutor")
    public String elimina(@RequestParam String id, ModelMap model) throws ErrorServicio {
        Tutor tutor = tutorServicio.buscarPorId(id);
        model.addAttribute("perfil", tutor);

        return "eliminaTutor.html";
    }
    
    @PostMapping("/eliminarTutor")
    public String eliminarTutor(@RequestParam String id) {
        
        try {
            tutorServicio.eliminarTutor(id);

        } catch (ErrorServicio e) {
            return "error.html";

        }
        return "index.html";
    }
    
    @PostMapping("/bajaTutor")
    public String bajaTutor(ModelMap modelo, @RequestParam String id) {
        
        try {
            tutorServicio.darDeBajaTutor(id);

        } catch (ErrorServicio e) {
            
             return "error.html";

        }
        modelo.put("titulo", "¡Ya no pertences a la comunidad de Tutores.com !");
        modelo.put("descripcion", "Puedes volver cuando quieras!! Te esperamos!!");
        return "exito.html";
    }

}

