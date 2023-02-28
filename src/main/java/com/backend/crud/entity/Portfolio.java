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
import jakarta.persistence.OneToOne;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String url;

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "portfolio_habilidad", joinColumns = @JoinColumn(name = "portfolio_id"),
    inverseJoinColumns = @JoinColumn(name = "habilidad_id"))
    private Set<Habilidad> habilidad = new HashSet<>();
  
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "portfolio_images", joinColumns = @JoinColumn(name = "portfolio_id"),
    inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<ImageModel> dataImage;


//    public Portfolio() {
//       
//    }
//
//    public Portfolio(String title, String description, byte[] imagen) {
//        this.title = title;
//        this.description = description;
//        this.imagen = imagen;
//    }

          
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Habilidad> getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Set<Habilidad> habilidad) {
        this.habilidad = habilidad;
    }    
    
    public Set<ImageModel> getDataImage() {
        return dataImage;
    }

    public void setDataImage(Set<ImageModel> dataImage) {
        this.dataImage = dataImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    
    
    
}
