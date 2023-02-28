package com.backend.crud.dto;

import com.backend.crud.entity.Habilidad;
import com.backend.crud.entity.ImageModel;
import java.util.HashSet;
import java.util.Set;

public class PortfolioDto {

    private String title;     
    private String description;
    private String url;
    private Set<Habilidad> habilidad = new HashSet<>();
    private Set<ImageModel> portfolioImages;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<ImageModel> getPortfolioImages() {
        return portfolioImages;
    }

    public void setPortfolioImages(Set<ImageModel> portfolioImages) {
        this.portfolioImages = portfolioImages;
    }

    
          
    
}
