/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.tutores.Controladores;

import com.web.tutores.Entidades.Usuario;
import com.web.tutores.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Agustín
 */
public class Controlador {

    @Autowired
    protected UsuarioServicio usuarioServicio;

    protected Usuario usuarioLogueado() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioServicio.buscarPorEmail(auth.getName());

        return usuario;
    }

}
