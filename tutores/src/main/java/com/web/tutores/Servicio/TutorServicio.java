package com.web.tutores.Servicio;

import com.web.tutores.Entidades.Foto;
import com.web.tutores.Entidades.Materia;
import com.web.tutores.Entidades.Tutor;
import com.web.tutores.Entidades.Zona;
import com.web.tutores.Enums.Rol;
import com.web.tutores.Errores.ErrorServicio;
import com.web.tutores.Repositorios.MateriaRepositorio;
import com.web.tutores.Repositorios.TutorRepositorio;
import com.web.tutores.Repositorios.ZonaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TutorServicio {

    @Autowired
    private ZonaRepositorio zonaRepositorio;
    
    @Autowired
    private MateriaRepositorio materiaRepositorio;

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private TutorRepositorio tutorRepositorio;
    

    @Transactional
    public void crearTutor(MultipartFile archivo, String nombre, String apellido, String mail, String clave, String clave2, String telefono, String idZona, String idMateria, String descripcion) throws ErrorServicio {
        Zona zona = zonaRepositorio.getOne(idZona);
        Materia materia = materiaRepositorio.getOne(idMateria);
        
        validarTutor(nombre, apellido, mail, clave, clave2, telefono, zona, materia);
        
        Tutor tutor = new Tutor();

        tutor.setNombre(nombre);
        tutor.setApellido(apellido);
        tutor.setMail(mail);
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        tutor.setClave(encriptada);
        tutor.setTelefono(telefono);
        tutor.setZona(zona);

        tutor.setAlta(new Date());
        tutor.setDescripcion(descripcion);
        
        List<Materia> materias = new ArrayList();
        materias.add(materia);
        tutor.setMaterias(materias);
        
        
        tutor.setRol(Rol.TUTOR);

        tutor.setFoto(fotoServicio.guardar(archivo));
        
        tutorRepositorio.save(tutor);

    }

//    @Transactional
//    public void creaMateriaTutor(String id, Materia materia) throws ErrorServicio {
//        List<Materia> materias = new ArrayList();
//
//        materias.add(materia);
//
//        Optional<Tutor> respuesta = tutorRepositorio.findById(id);
//
//        if (respuesta.isPresent()) {
//
//            Tutor tutor = respuesta.get();
//
//            tutor.setMaterias(materias);
//
//            tutorRepositorio.save(tutor);
//
//        } else {
//            throw new ErrorServicio("No se encontró al tutor");
//        }
//
//    }

    @Transactional
    public void agregaMateriaTutor(String id, Materia materia) throws ErrorServicio {

        Optional<Tutor> respuesta = tutorRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Tutor tutor = respuesta.get();

            List<Materia> materias = tutor.getMaterias();

            materias.add(materia);

            tutor.setMaterias(materias);

            tutorRepositorio.save(tutor);

        } else {
            throw new ErrorServicio("No se encontró al tutor");
        }

    }

    @Transactional
    public void modificarTutor(MultipartFile archivo, String id, String nombre, String apellido, String mail, String clave, String clave2, String telefono, Zona zona, List<Materia> materias, String descripcion) throws ErrorServicio {

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
            
            Foto foto = fotoServicio.guardar(archivo);
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

    private void validarTutor(String nombre, String apellido, String mail, String clave, String clave2, String telefono, Zona zona, Materia materia) throws ErrorServicio {

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
        if (!clave2.equals(clave)) {
            throw new ErrorServicio("La clave 2 no es igual a la primera ingresada");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El telefono no puede ser nulo.");
        }
        if (zona == null) {
            throw new ErrorServicio("La zona no puede ser nula.");
        }
        if (materia == null) {
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

    public List<Tutor> listarActivos(String q) {
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
    
//    @Override
//    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
//        Usuario usuario = usuarioRepositorio.buscarPorMail(mail);
//        if (usuario != null) {
//
//            List<GrantedAuthority> permisos = new ArrayList<>();
//
//            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());
//            permisos.add(p1);
//   
//            
//
//            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//            HttpSession session = attr.getRequest().getSession();
//            session.setAttribute("usuariosession", usuario);
//
//            User user = new User(usuario.getMail(), usuario.getClave(), permisos);
//            return user;
//        } else {
//            return null;
//        }
//    }
    

}
