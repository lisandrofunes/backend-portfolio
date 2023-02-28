package com.backend.crud.dto;

//import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.NotBlank;


public class HabilidadDto {

    @NotBlank
    private String nombre;
    
    @NotBlank
    private String imagen;

    public HabilidadDto() {
    }

    public HabilidadDto(@NotBlank String nombre,@NotBlank String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
