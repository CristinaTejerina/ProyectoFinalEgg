/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.tutores.Controladores;

import com.web.tutores.Entidades.Zona;
import com.web.tutores.Errores.ErrorServicio;
import com.web.tutores.Repositorios.ZonaRepositorio;
import com.web.tutores.Servicio.ZonaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/zona")
public class ZonaControlador {
    
     @Autowired
    private ZonaServicio zonaServicio;
     
     @Autowired
    private ZonaRepositorio zonaRepositorio;
    
    @PostMapping("/registraZona")
    public String registraZona(ModelMap modelo, @RequestParam String nombre, @RequestParam String descripcion) {
        try {
            zonaServicio.agregarZona(nombre, descripcion);
        } catch (ErrorServicio ex) {
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);
            
            return "crearZona.html";
        }
        
        modelo.put("titulo", "Hay una nueva zona disponible!");

        return "configuracionGral.html";
    }
    
    
    
    @PostMapping("/cambioZona")
    public String cambioDeZona(ModelMap modelo, @RequestParam String nombre, @RequestParam String descripcion, @RequestParam String id) {
               
        try {
            zonaServicio.editarZona(id,nombre, descripcion);
            
        } catch (ErrorServicio ex) {
            
            List<Zona> zonas = zonaRepositorio.findAll();
            modelo.put("zonas", zonas);
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);
            
            return "editarZona.html";
        }
        
        modelo.put("titulo", "Se edito correctamente!");

        return "configuracionGral.html";
    }
    
}
