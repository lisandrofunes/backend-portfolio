package com.backend.crud.controller;

import com.backend.crud.dto.Mensaje;
import com.backend.crud.dto.HabilidadDto;
import com.backend.crud.entity.Habilidad;
import com.backend.crud.service.HabilidadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habilidad")
@CrossOrigin(origins = "*")
public class HabilidadController {

    @Autowired
    HabilidadService habilidadService;

    @GetMapping("/lista")
    public ResponseEntity<List<Habilidad>> list(){
        List<Habilidad> list = habilidadService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id") int id){
        if(!habilidadService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Habilidad habilidad = habilidadService.getOne(id).get();
        return new ResponseEntity(habilidad, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Habilidad> getByNombre(@PathVariable("nombre") String nombre){
        if(!habilidadService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Habilidad habilidad = habilidadService.getByNombre(nombre).get();
        return new ResponseEntity(habilidad, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody HabilidadDto habilidadDto){
        if(StringUtils.isBlank(habilidadDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(habilidadDto.getImagen()==null || habilidadDto.getImagen()==null )
            return new ResponseEntity(new Mensaje("debe ingresar una direccion para la imagen"), HttpStatus.BAD_REQUEST);
        if(habilidadService.existsByNombre(habilidadDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Habilidad producto = new Habilidad(habilidadDto.getNombre(), habilidadDto.getImagen());
        habilidadService.save(producto);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody HabilidadDto habilidadDto){
        if(!habilidadService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(habilidadService.existsByNombre(habilidadDto.getNombre()) && habilidadService.getByNombre(habilidadDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(habilidadDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(habilidadDto.getImagen()==null || habilidadDto.getImagen()==null )
            return new ResponseEntity(new Mensaje("debe ingresar una direccion para la imagen"), HttpStatus.BAD_REQUEST);

        Habilidad habilidad = habilidadService.getOne(id).get();
        habilidad.setNombre(habilidadDto.getNombre());
        habilidad.setImagen(habilidadDto.getImagen());
        habilidadService.save(habilidad);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!habilidadService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        habilidadService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }


}
