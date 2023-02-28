package com.backend.crud.dto;

//import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.NotBlank;


public class PersonaDto {

    private String saludo;
    private String nombre;
    private String dedicacion;

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
    
    


}
