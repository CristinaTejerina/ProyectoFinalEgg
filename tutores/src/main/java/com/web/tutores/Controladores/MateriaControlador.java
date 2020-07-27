package com.web.tutores.Controladores;

import com.web.tutores.Enums.Asignatura;
import com.web.tutores.Enums.NivelEducativo;
import com.web.tutores.Errores.ErrorServicio;
import com.web.tutores.Servicio.MateriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/materia")
public class MateriaControlador {

    @Autowired
    private MateriaServicio materiaServicio;
    
    @PostMapping("/registraMateria")
    public String registraMateria(ModelMap modelo, @RequestParam String nombre, @RequestParam String descripcion, @RequestParam Asignatura asignatura, @RequestParam NivelEducativo nivel) {
        try {
            materiaServicio.agregarMateria(nombre, descripcion, asignatura, nivel);
        } catch (ErrorServicio ex) {
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);
            modelo.put("asignatura", asignatura);
            modelo.put("nivel", nivel);
            
            return "crearMateria.html";
        }
        
        modelo.put("titulo", "Tu materia fue registrado correctamene!");

        return "configuracionGral.html";
    }
    
    
    
    
    @GetMapping("/modificar")
    public String modificar(ModelMap modelo) {

        return "modificar.html";
    }

    @GetMapping("/deshabilitar")
    public String deshabilitar(ModelMap modelo) {

        return "deshabilitar.html";

    }

    @GetMapping("/habilitar")
    public String habilitar(ModelMap modelo) {

        return "habilitar.html";
    }

}
