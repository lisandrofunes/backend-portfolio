package com.backend.crud.service;

import com.backend.crud.entity.Portfolio;
import com.backend.crud.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepository;

//    public List<Portfolio> list(){
//        return portfolioRepository.findAll();
//    }

    public List<Portfolio> getPortfolios(){
        return (List<Portfolio>) portfolioRepository.findAll();
    }
    
    public Optional<Portfolio> getOne(int id){
        return portfolioRepository.findById(id);
    }

    public Optional<Portfolio> getByTitle(String title){
        return portfolioRepository.findByTitle(title);
    }

//    public void save(Portfolio portfolio){
//        portfolioRepository.save(portfolio);
//    }
    public Portfolio save(Portfolio portfolio){
        return portfolioRepository.save(portfolio);
    }

    public void delete(int id){
        portfolioRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return portfolioRepository.existsById(id);
    }

    public boolean existsByTitle(String title){
        return portfolioRepository.existsByTitle(title);
    }
}
