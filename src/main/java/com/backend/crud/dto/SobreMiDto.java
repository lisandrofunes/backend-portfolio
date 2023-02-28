package com.backend.crud.dto;

import com.backend.crud.entity.ImageModel;
import java.util.ArrayList;
import java.util.Set;


public class SobreMiDto {

    private String texto;

    private Set<ImageModel> dataImage; 


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Set<ImageModel> getDataImage() {
        return dataImage;
    }

    public void setDataImage(Set<ImageModel> dataImage) {
        this.dataImage = dataImage;
    }

    

}
