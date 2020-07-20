package com.web.tutores.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/materia")
public class MateriaControlador {

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
