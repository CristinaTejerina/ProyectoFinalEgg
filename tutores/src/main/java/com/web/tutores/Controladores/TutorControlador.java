package com.web.tutores.Controladores;



import org.springframework.stereotype.Controller;

import com.web.tutores.Entidades.Tutor;

import com.web.tutores.Entidades.Zona;

import com.web.tutores.Repositorios.ZonaRepositorio;
import com.web.tutores.Servicio.TutorServicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TutorControlador {


    @Autowired
    private ZonaRepositorio zonaRepositorio;

    @Autowired
    private TutorServicio tutorServicio;

    @GetMapping("/registroTutor")
    public String registro(ModelMap modelo) {
        List<Zona> zonas = zonaRepositorio.findAll();
        modelo.put("zonas", zonas);
        return "registroTutor.html";
    }

    @GetMapping("/modificar")
    public String modificar(ModelMap modelo) {
        List<Zona> zonas = zonaRepositorio.findAll();
        modelo.put("zonas", zonas);
        return "modificar.html";
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

    // hay que meterle mano!!!
    @PostMapping("/registrarTutor")
    public String registrar(ModelMap modelo, MultipartFile archivo,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String mail,
            @RequestParam String clave,
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
        return null;

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
