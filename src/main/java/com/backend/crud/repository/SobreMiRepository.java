/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.backend.crud.repository;

import com.backend.crud.entity.SobreMi;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lisandro
 */
public interface SobreMiRepository extends JpaRepository<SobreMi, Integer> {
    Optional<SobreMi> findById (Integer id);
    boolean existsById (Integer id);
    
}
