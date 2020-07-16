package com.web.tutores.Entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Tutor extends Usuario {
    
    private String descripcion;
    
    @OneToMany
    private List<Materia> materias = new ArrayList<Materia>();
    
    
    
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
    

}

