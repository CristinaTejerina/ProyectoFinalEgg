package com.web.tutores.Repositorios;

import com.web.tutores.Entidades.Tutor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TutorRepositorio extends JpaRepository<Tutor, String>{
    
    @Query("SELECT a FROM Tutor a WHERE (a.nombre = :nombre AND a.apellido = :apellido)")
    public Tutor buscarPorNombre(@Param("nombre") String nombre, @Param("apellido") String apellido);
    
    @Query("SELECT a FROM Tutor a ORDER BY a.apellido")
    public List <Tutor> listaTutores();
    
    @Query("SELECT a FROM Tutor a WHERE(a.zona = :nombre)")
    public List<Tutor> buscarPorZona(@Param("nombre") String nombre);
    
    @Query("SELECT a FROM Tutor a WHERE(a.materia = :nombre)")
    public List<Tutor> buscarPorMateria(@Param("nombre") String nombre);
    
    
}
