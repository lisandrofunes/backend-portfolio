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
import java.util.ArrayList;
import java.util.Set;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class SobreMi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String texto;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "sobreMi_images", joinColumns = @JoinColumn(name = "sobreMi_id"),
    inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<ImageModel> dataImage; 

    public SobreMi() {
    }

    public SobreMi(String texto, Set<ImageModel> dataImage) {
        this.texto = texto;
        this.dataImage = dataImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
