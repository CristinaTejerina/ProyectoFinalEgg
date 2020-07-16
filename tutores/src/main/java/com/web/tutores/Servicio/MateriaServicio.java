
package com.web.tutores.Servicio;

import com.web.tutores.Errores.ErrorServicio;
import com.web.tutores.Repositorios.MateriaRepositorio;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaServicio {
    
    @Autowired
    private MateriaRepositorio materiaRepositorio;
    
    @Transactional
    public void agregarMateria(String nombre, String descripcion, Date alta, Asignaturas asignatura, NivelEducativo nivel){
        
        validar(nombre, asignatura, nivel);
    }
    
    
    public void validar(String nombre, Asignaturas asignatura, NivelEducativo nivel) extends ErrorServicio throws ErrorServicio{
        if(nombre == null || nombre.isEmpty()){
            throw new ErrorServicio("El nombre de la mascota no puede ser nulo o vacio");
        }
        
        if(asignatura == null){
            
            throw new ErrorServicio("La asignatura no puede ser nula");
        }
        
        if(nivel == null){
            
            throw new ErrorServicio("El nivel educativo no puede ser nulo");
        }
    }
    
}
