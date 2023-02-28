/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.crud.service;

import com.backend.crud.entity.SobreMi;
import com.backend.crud.repository.SobreMiRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SobreMiService {
    
    @Autowired
    SobreMiRepository sobreMiRepository;
    
    public List<SobreMi> list(){
        return sobreMiRepository.findAll();
    }

    public Optional<SobreMi> getOne(int id){
        return sobreMiRepository.findById(id);
    }

//    public Optional<SobreMi> getByNombre(String nombre){
//        return sobreMiRepository.findByNombre(nombre);
//    }

    public void  save(SobreMi persona){
        sobreMiRepository.save(persona);
    }

    public void delete(int id){
        sobreMiRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return sobreMiRepository.existsById(id);
    }


}
