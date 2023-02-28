package com.backend.crud.controller;

import com.backend.crud.dto.Mensaje;
import com.backend.crud.dto.PersonaDto;
import com.backend.crud.entity.ImageModel;
import com.backend.crud.entity.Persona;
import com.backend.crud.entity.Portfolio;
import com.backend.crud.service.PersonaService;
import java.io.IOException;
import java.util.HashSet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "*")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Persona persona = personaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Persona> getByNombre(@PathVariable("nombre") String nombre){
        if(!personaService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Persona persona = personaService.getByNombre(nombre).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestBody PersonaDto personaDto){
//        if(StringUtils.isBlank(personaDto.getNombre()))
//            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
//        if(personaDto.getApellido()==null || personaDto.getApellido()==null )
//            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
//        if(personaService.existsByNombre(personaDto.getNombre()))
//            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
//        Persona producto = new Persona(personaDto.getNombre(), personaDto.getApellido());
//        personaService.save(producto);
//        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
//    }
    
//    ------------
    @PostMapping(value = {"/create"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> save(@RequestPart("persona") PersonaDto personaDto,
            @RequestPart("imageFile") MultipartFile[] file) {
        
        if(StringUtils.isBlank(personaDto.getNombre())){
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(personaDto.getDedicacion()==null || personaDto.getDedicacion()==null ){
            return new ResponseEntity(new Mensaje("La dedicacion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if(personaDto.getSaludo()==null || personaDto.getSaludo()==null ){
            return new ResponseEntity(new Mensaje("El saludo es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        
        Set<ImageModel> image;
        try {
            Set<ImageModel> images = uploadImage(file);
            image = images;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        Persona persona = new Persona(personaDto.getSaludo(), personaDto.getNombre(), personaDto.getDedicacion(), image);
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona creada"), HttpStatus.OK);
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
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestPart("persona") PersonaDto personaDto, @RequestPart("imageFile") MultipartFile[] file){
        if(!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
//        if(personaService.existsByNombre(personaDto.getNombre()) && personaService.getByNombre(personaDto.getNombre()).get().getId() != id)
//            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personaDto.getNombre())){
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(personaDto.getDedicacion()==null || personaDto.getDedicacion()==null ){
            return new ResponseEntity(new Mensaje("La dedicacion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if(personaDto.getSaludo()==null || personaDto.getSaludo()==null ){
            return new ResponseEntity(new Mensaje("El saludo es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        
        Set<ImageModel> image;
        try {
            Set<ImageModel> images = uploadImage(file);
            image = images;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        Persona persona = personaService.getOne(id).get();
        persona.setSaludo(personaDto.getSaludo());
        persona.setNombre(personaDto.getNombre());
        persona.setDedicacion(personaDto.getDedicacion());
        persona.setDataImage(image);
 
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!personaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        personaService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }


}
