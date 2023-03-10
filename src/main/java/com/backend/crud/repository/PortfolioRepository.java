package com.backend.crud.repository;


import com.backend.crud.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    Optional<Portfolio> findByTitle(String title);
    boolean existsByTitle(String title);
}
