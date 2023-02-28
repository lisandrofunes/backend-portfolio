/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.crud.service;

import com.backend.crud.entity.ImageModel;
import com.backend.crud.repository.ImageRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageService {
    
    @Autowired
    ImageRepository imageRepository;
    
    public Optional<ImageModel> getOne(long id){
        return imageRepository.findById(id);
    }
    
    public Optional<ImageModel> getByName(String name){
        return imageRepository.findByName(name);
    }
        
    public ImageModel save(ImageModel image){
        return imageRepository.save(image);
    }
    
}
