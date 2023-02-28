package com.backend.crud.repository;

import com.backend.crud.entity.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Integer> {
    Optional<Habilidad> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
