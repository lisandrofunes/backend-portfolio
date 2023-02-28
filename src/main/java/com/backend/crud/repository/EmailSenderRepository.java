package com.backend.crud.repository;

import com.backend.crud.entity.EmailSender;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSenderRepository extends JpaRepository<EmailSender, Integer> {
//    Optional<EmailSender> findByNombre(String nombre);
//    boolean existsByNombre(String nombre);
}
