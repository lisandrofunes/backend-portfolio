/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.crud.controller;

import com.backend.crud.dto.Mensaje;
import com.backend.crud.dto.SobreMiDto;
import com.backend.crud.entity.ImageModel;
import com.backend.crud.entity.SobreMi;
import com.backend.crud.service.SobreMiService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/about")
@CrossOrigin(origins = {"https://lisandro-funes.web.app"})
public class SobreMiController {
    
    @Autowired
    SobreMiService sobreMiService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<SobreMi>> list(){
        List<SobreMi> list = sobreMiService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<SobreMi> getById(@PathVariable("id") int id){
        if(!sobreMiService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        SobreMi sobreMi = sobreMiService.getOne(id).get();
        return new ResponseEntity(sobreMi, HttpStatus.OK);
    }

       
    @PostMapping(value = {"/create"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> save(@RequestPart("sobreMi") SobreMiDto sobreMiDto,
            @RequestPart("imageFile") MultipartFile[] file) {
        
        if(StringUtils.isBlank((CharSequence) sobreMiDto.getTexto())){
            return new ResponseEntity(new Mensaje("el texto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Set<ImageModel> image;
        try {
            Set<ImageModel> images = uploadImage(file);
            image = images;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        SobreMi sobreMi = new SobreMi(sobreMiDto.getTexto(), image);
        sobreMiService.save(sobreMi);
        return new ResponseEntity(new Mensaje("SobreMi creada"), HttpStatus.OK);
//        return portfolioService.save(portfolio);
        
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
            
        }
        return imageModels;
    }
//    ------------

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value= {"/update/{id}"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestPart("sobreMi") SobreMiDto sobreMiDto, @RequestPart("imageFile") MultipartFile[] file){
        if(!sobreMiService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
//        if(sobreMiService.existsByNombre(sobreMiDto.getNombre()) && sobreMiService.getByNombre(sobreMiDto.getNombre()).get().getId() != id)
//            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank((CharSequence) sobreMiDto.getTexto())){
            return new ResponseEntity(new Mensaje("el texto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Set<ImageModel> image;
        try {
            Set<ImageModel> images = uploadImage(file);
            image = images;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        SobreMi sobreMi = sobreMiService.getOne(id).get();
        sobreMi.setTexto(sobreMiDto.getTexto());
        sobreMi.setDataImage(image);
 
        sobreMiService.save(sobreMi);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!sobreMiService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        sobreMiService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }

}
