package com.web.tutores.Servicio;

import com.web.tutores.Entidades.Foto;
import com.web.tutores.Entidades.Usuario;
import com.web.tutores.Entidades.Zona;
import com.web.tutores.Errores.ErrorServicio;
import com.web.tutores.Repositorios.UsuarioRepositorio;
import com.web.tutores.Repositorios.ZonaRepositorio;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private ZonaRepositorio zonaRepositorio;

    public Usuario registrar(MultipartFile archivo, String nombre, String apellido, String mail, String clave, String idZona) throws ErrorServicio {
        Zona zona = zonaRepositorio.getOne(mail);
        validar(nombre,apellido,mail,clave,zona);
        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);

        usuario.setZona(zona);
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(clave);
        usuario.setAlta(new Date());

        Foto foto = fotoServicio.guardar(archivo);
        usuarioRepositorio.save(usuario);

        return usuario;
    }

    @Transactional
    public void modificar(MultipartFile archivo, String id, String nombre, String apellido, String mail, String clave, String idZona) throws ErrorServicio {
        Zona zona = zonaRepositorio.getOne(idZona);
        
        validar(nombre, apellido, mail, clave,zona);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setMail(mail);
            String encriptada = new BCryptPasswordEncoder().encode(clave);
            usuario.setClave(encriptada);
            usuario.getZona();

            String idFoto = null;

            if (usuario.getFoto() != null) {
                idFoto = usuario.getFoto().getId();
            }
            Foto foto = fotoServicio.actualizar(idFoto, archivo);
            usuario.setFoto(foto);

            usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No se encontro el usuario solicitado.");
        }
    }

    @Transactional
    public void deshabilitar(String id) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setBaja(new Date());
            usuarioRepositorio.save(usuario);

        } else {
            throw new ErrorServicio("No se encuentra el usuario solicitado para dar de baja.");
        }
    }

    @Transactional
    public void habilitar(String id) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setBaja(null);

            usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No se encontro el usuario solicitado");

        }
    }

    public void validar(String nombre, String apellido, String mail, String clave, String clave2, Zona zona) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del usuario no puede ser nulo");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido del usuario no puede ser nulo");
        }
        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El mail del usuario no puede ser nulo");
        }
        if (clave == null || clave.isEmpty() || clave.length() <= 6) {
            throw new ErrorServicio("El clave del usuario no puede ser nulo y tiene que tener mas de 6 digitos ");
        }

        if (zona == null) {
            throw new ErrorServicio("No se encontro la zona solicitada");
        }
    }

    public Usuario buscarporId(String id) {
        return usuarioRepositorio.getOne(id);
    }

    private void validar(String nombre, String apellido, String mail, String clave, Zona zona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
