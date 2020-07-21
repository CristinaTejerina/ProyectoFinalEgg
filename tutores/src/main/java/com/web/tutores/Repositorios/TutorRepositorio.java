package com.web.tutores.Repositorios;

import com.web.tutores.Entidades.Tutor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepositorio extends JpaRepository<Tutor, String> {

    @Query("SELECT a FROM Tutor a WHERE a.nombre = :nombre AND a.apellido = :apellido")
    public Tutor buscarPorNombre(@Param("nombre") String nombre, @Param("apellido") String apellido);

    @Query("SELECT a FROM Tutor a ORDER BY a.apellido ")
    public List<Tutor> listaTutores();

    @Query("SELECT a FROM Tutor a WHERE a.zona.nombre = :nombre")
    public List<Tutor> buscarPorZona(@Param("nombre") String nombre);

    @Query("SELECT a FROM Tutor a, IN(a.materias) m WHERE m.nombre = :nombre")
    public List<Tutor> buscarPorMateria(@Param("nombre") String nombre);

//    @Query("SELECT a from Tutor a WHERE a.baja IS NULL AND a.nombre LIKE :nombre")
//    public Page<Tutor> buscarActivos(Pageable pageable, @Param("nombre") String nombre);
    
    @Query("SELECT a from Tutor a WHERE a.baja IS NULL")
    public List<Tutor> buscarActivos();

    @Query("SELECT h from Tutor h "
            + ", IN(h.materias) v"
            + " WHERE h.baja IS NULL "
            + " AND h.nombre LIKE :q "
            + " OR h.apellido LIKE :q "
            + "OR h.zona.nombre LIKE :q "
            + " OR v.nombre LIKE :q "
            + " OR v.nivelEducativo LIKE :q "
            + " OR v.asignatura LIKE :q ")
    public List<Tutor> buscarActivos(@Param("q") String q);

}
