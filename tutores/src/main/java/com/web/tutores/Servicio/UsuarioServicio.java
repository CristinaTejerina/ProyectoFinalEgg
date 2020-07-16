package com.web.tutores.Servicio;

import com.web.tutores.Entidades.Usuario;
import com.web.tutores.Entidades.Zona;
import com.web.tutores.Repositorios.UsuarioRepositorio;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
//    @Autowired
//    private FotoServicio fotoServicio;
//    
//    @Autowired
//    private ZonaRepositorio zonaRepositorio;
//    

    public Usuario registrar(String nombre, String apellido, String mail, String clave, String idZona) {
        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);

        //usuario.setZona(zona);
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(clave);
        usuario.setAlta(new Date());

        // Foto foto= fotoServicio.guardar(archivo);
        usuarioRepositorio.save(usuario);

        return usuario;
    }

//    @Transactional
//    public void modificar(String id, String nombre, String apellido, String mail, String clave, String idZona) {
    //Zona zona = zonaRepositorio.getOne(idZona);
    //validar(nombre,apellido,mail,clave,idZona);
    //Optional<Usuario> respuesta=usuarioRepositorio.findById(id)
//        if (respuesta.isPresent()) {
//            Usuario usuario = respuesta.get();
//            usuario.setNombre(nombre);
//            usuario.setApellido(apellido);
//            usuario.setMail(mail);
//            String encriptada = new BCryptPasswordEncoder().encode(clave);
//            usuario.setClave(encriptada);
//            usuario.getZona();
//
//            if (usuario.getFoto() != null) {
//                idFoto = usuario.getFoto().getId();
//            }
//            Foto foto = fotoServicio.actualizar(idFoto, archivo);
//            usuario.setFoto(foto);
//
//            usuarioRepositorio.save(usuario);
//        } else {
//            throw new ErrorServicio("No se encontro el usuario solicitado.");
//        }
//    }
//        @Transactional
//        public void deshabilitar (String id) throws ErrorServicio
//        {
    //Optional<Usuario> respuesta=usuarioRepositorio.findById(id);
//    if(respuesta.isPresent()){
//        Usuario usuario= respuesta.get();
//        usuario.setBaja(new Date());
//        usuarioRepositorio.save(usuario);
//        
//        
//    }else{
    //throw new ErrorServicio("No se encuentra el usuario solicitado para dar de baja.");
//    }
//}
//    @Transactional
//    public void habilitar(String id) throws ErrorServicio {
//        Optional<Usuario>respuesta=usuarioRepositorio.findById(id);
//        if(respuesta.isPresent()){
//            Usuario usuario= respuesta.get();
//            usuario.setBaja(null);
//            
//            usuarioRepositorio.save(id);
//        }else {
//            throw new ErrorServicio("No se encontro el usuario solicitado");
//
//    }
//     public void validar(String nombre, String apellido, String mail, String clave, String clave2, Zona zona) throws ErrorServicio {
//        if (nombre == null || nombre.isEmpty()) {
//            throw new ErrorServicio("El nombre del usuario no puede ser nulo");
//        }
//        if (apellido == null || apellido.isEmpty()) {
//            throw new ErrorServicio("El apellido del usuario no puede ser nulo");
//        }
//        if (mail == null || mail.isEmpty()) {
//            throw new ErrorServicio("El mail del usuario no puede ser nulo");
//        }
//        if (clave == null || clave.isEmpty() || clave.length() <= 6) {
//            throw new ErrorServicio("El clave del usuario no puede ser nulo y tiene que tener mas de 6 digitos ");
//        }
//
//        if (!clave.equals(clave2)) {
//            throw new ErrorServicio(("Las claves deben ser iguales"));
//        }
//         if (zona == null) {
//                throw new ErrorServicio("No se encontro la zona solicitada");
//            }
//    }
//    public Uusario buscarporId(String id){
//       return usuarioRepositorio.getOne(id);
//    }
}
