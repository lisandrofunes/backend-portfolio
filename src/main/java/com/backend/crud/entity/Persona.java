package com.backend.crud.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String saludo;
    private String nombre;
    private String dedicacion;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "persona_images", joinColumns = @JoinColumn(name = "persona_id"),
    inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<ImageModel> dataImage; 

    public Persona() {
    }

    public Persona(String saludo, String nombre, String dedicacion, Set<ImageModel> dataImage) {
        this.saludo = saludo;
        this.nombre = nombre;
        this.dedicacion = dedicacion;
        this.dataImage = dataImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSaludo() {
        return saludo;
    }

    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }

    public String getDedicacion() {
        return dedicacion;
    }

    public void setDedicacion(String dedicacion) {
        this.dedicacion = dedicacion;
    }

    public Set<ImageModel> getDataImage() {
        return dataImage;
    }

    public void setDataImage(Set<ImageModel> dataImage) {
        this.dataImage = dataImage;
    }

    

}
