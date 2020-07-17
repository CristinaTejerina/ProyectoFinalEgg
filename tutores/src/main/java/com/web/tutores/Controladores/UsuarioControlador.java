<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.tutores.Controladores;

/**
 *
 * @author RENZO
 */
public class UsuarioControlador {
    
=======
package com.web.tutores.Controladores;

import com.web.tutores.Entidades.Zona;
import com.web.tutores.Errores.ErrorServicio;
import com.web.tutores.Repositorios.ZonaRepositorio;
import com.web.tutores.Servicio.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ZonaRepositorio zonaRepositorio;

//    @GetMapping("/registro")
//    public String registro(ModelMap modelo) {
//        List<Zona> zonas = zonaRepositorio.findAll();
//        modelo.put("zonas", zonas);
//        return "registro.html";
//    }

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

//    @PostMapping("/registrar")
//    public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, String idZona) {
//        try {
//            usuarioServicio.registrar(null, nombre, apellido, mail, clave1, idZona);
//        } catch (ErrorServicio ex) {
//
//            List<Zona> zonas = zonaRepositorio.findAll();
//            modelo.put("zonas", zonas);
//            modelo.put("error", ex.getMessage());
//            modelo.put("nombre", nombre);
//            modelo.put("apellido", apellido);
//            modelo.put("maiil", mail);
//            modelo.put("clave1", clave1);
//         
//            return "registro.html";
//        }
//        modelo.put("titulo", "¡Bienvenido a la comunidad de Tinder !");
//        modelo.put("descripcion", "Tu usuario fue registrado correctamene, ¡¡Bienvenido!!");
//        return "exito.html";
//    }

>>>>>>> 6900c0ca2c46330d7b399fa96688f4d300c7c4f9
}
