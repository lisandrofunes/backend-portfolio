package com.backend.crud.service;

import com.backend.crud.entity.Habilidad;
import com.backend.crud.repository.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HabilidadService {

    @Autowired
    HabilidadRepository habilidadRepository;

    public List<Habilidad> list(){
        return habilidadRepository.findAll();
    }

    public Optional<Habilidad> getOne(int id){
        return habilidadRepository.findById(id);
    }

    public Optional<Habilidad> getByNombre(String nombre){
        return habilidadRepository.findByNombre(nombre);
    }

    public void save(Habilidad habilidad){
        habilidadRepository.save(habilidad);
    }

    public void delete(int id){
        habilidadRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return habilidadRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return habilidadRepository.existsByNombre(nombre);
    }
}
