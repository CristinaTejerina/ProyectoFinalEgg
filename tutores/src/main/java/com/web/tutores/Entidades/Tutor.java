package com.web.tutores.Entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Tutor extends Usuario{

//    //GENERADOR DE ID AUTOMATICO
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    private String id;
//    private String nombre;
//    private String apellido;
//    private String mail;
//    private String clave;
//    private String telefono;
//
//    @OneToOne
//    private Zona zona;
//
//    @OneToOne
//    private Foto foto;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date alta;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date baja;
    
    private String descripcion;

    @OneToMany
    private List<Materia> materias;

//    public Tutor(String id, String nombre, String apellido, String mail, String clave, String telefono, Zona zona, Foto foto, Date alta, Date baja) {
//        super(id, nombre, apellido, mail, clave, telefono, zona, foto, alta, baja);
//    }

    public Tutor() {

    }


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
