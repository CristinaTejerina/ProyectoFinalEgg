package com.web.tutores.Servicio;

import com.web.tutores.Entidades.Foto;
import com.web.tutores.Entidades.Materia;
import com.web.tutores.Entidades.Tutor;
import com.web.tutores.Entidades.Zona;
import com.web.tutores.Enums.Rol;
import com.web.tutores.Errores.ErrorServicio;
import com.web.tutores.Repositorios.TutorRepositorio;
import com.web.tutores.Repositorios.UsuarioRepositorio;
import com.web.tutores.Repositorios.ZonaRepositorio;
import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TutorServicio {

    @Autowired
    private ZonaRepositorio zonaRepositorio;

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private TutorRepositorio tutorRepositorio;

    @Transactional
    public void crearTutor(MultipartFile archivo, String nombre, String apellido, String mail, String clave, String telefono, String idZona, List<Materia> materias, String descripcion) throws ErrorServicio {
        Zona zona = zonaRepositorio.getOne(idZona);
        validarTutor(nombre, apellido, mail, clave, telefono, zona, materias);

        Tutor tutor = new Tutor();

        tutor.setNombre(nombre);
        tutor.setApellido(apellido);
        tutor.setMail(mail);
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        tutor.setClave(encriptada);
        tutor.setTelefono(telefono);
        tutor.setZona(zona);

        tutor.setAlta(new Date());
        tutor.setBaja(null);
        tutor.setDescripcion(descripcion);
        tutor.setMaterias(materias);
        tutor.setRol(Rol.TUTOR);

        tutor.setFoto(fotoServicio.guardar(archivo));
        tutorRepositorio.save(tutor);

    }

    @Transactional
    public void modificarTutor(String id, String nombre, String apellido, String mail, String clave, String clave2, String telefono, Zona zona, Foto foto, List<Materia> materias, String descripcion) throws ErrorServicio {

        validarTutor2(nombre, apellido, mail, clave, clave2, telefono, zona, materias);

        Optional<Tutor> respuesta = tutorRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Tutor tutor = respuesta.get();

            tutor.setNombre(nombre);
            tutor.setApellido(apellido);
            tutor.setMail(mail);
            String encriptada = new BCryptPasswordEncoder().encode(clave);
            tutor.setClave(encriptada);
            tutor.setTelefono(telefono);
            tutor.setZona(zona);
            tutor.setFoto(foto);
            tutor.setMaterias(materias);

            tutorRepositorio.save(tutor);

        } else {

            throw new ErrorServicio("No se encontró al tutor");
        }
    }

    public void eliminarTutor(String id) throws ErrorServicio {

        Optional<Tutor> respuesta = tutorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Tutor tutor = respuesta.get();
            tutorRepositorio.delete(tutor);

        } else {
            throw new ErrorServicio("No se encontró al tutor");
        }
    }

    private void validarTutor(String nombre, String apellido, String mail, String clave, String telefono, Zona zona, List<Materia> materias) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo.");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido no puede ser nulo.");
        }
        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El mail no puede ser nulo.");
        }
        if (clave == null || clave.isEmpty() || clave.length() <= 6) {
            throw new ErrorServicio("La clave no puede ser nula o menor a 6 caracteres.");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El telefono no puede ser nulo.");
        }
        if (zona == null) {
            throw new ErrorServicio("La zona no puede ser nula.");
        }
        if (materias == null || materias.isEmpty()) {
            throw new ErrorServicio("La/s materias no pueden ser nulas.");
        }

    }

    private void validarTutor2(String nombre, String apellido, String mail, String clave, String clave2, String telefono, Zona zona, List<Materia> materias) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo.");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido no puede ser nulo.");
        }
        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El mail no puede ser nulo.");
        }
        if (clave == null || clave.isEmpty() || clave.length() <= 6) {
            throw new ErrorServicio("La clave no puede ser nula o menor a 6 caracteres.");
        }
        if (!clave.equals(clave2)) {
            throw new ErrorServicio("Las claves deben coincidir");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El telefono no puede ser nulo.");
        }
        if (zona == null) {
            throw new ErrorServicio("La zona no puede ser nula.");
        }
        if (materias == null || materias.isEmpty()) {
            throw new ErrorServicio("La/s materias no pueden ser nulas.");
        }

    }

    public Tutor buscarPorNombreApellido(String nombre, String apellido) {
        return tutorRepositorio.buscarPorNombre(nombre, apellido);
    }

    public List<Tutor> tutoresDisponibles() {
        return tutorRepositorio.listaTutores();
    }

    public List<Tutor> buscarPorZona(String nombre) {
        return tutorRepositorio.buscarPorZona(nombre);
    }

    public List<Tutor> buscarPorMateria(String nombre) {
        return tutorRepositorio.buscarPorMateria(nombre);
    }

    public List<Tutor> listarActivos( String q) {
        return tutorRepositorio.buscarActivos("%" + q + "%");
    }
    
    public List<Tutor> listarActivos() {
        return tutorRepositorio.buscarActivos();
    }
    
    public Tutor buscarPorId(String id) throws ErrorServicio{
        Optional<Tutor> respuesta = tutorRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Tutor tutor = respuesta.get();
            return tutor;
            
        }else{
            throw new ErrorServicio("No se encontro el usuario solicitado.");
        }
    }

}
