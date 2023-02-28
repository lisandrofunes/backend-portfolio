package com.backend.crud.controller;

import com.backend.crud.dto.Mensaje;
import com.backend.crud.dto.PortfolioDto;
import com.backend.crud.entity.ImageModel;

import com.backend.crud.entity.Portfolio;

import com.backend.crud.service.PortfolioService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;
import org.springframework.http.MediaType;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/portfolio")
@CrossOrigin(origins = {"https://lisandro-funes.web.app"})
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

//    @GetMapping("/lista")
//    public ResponseEntity<List<Portfolio>> list() {
//        List<Portfolio> list = portfolioService.list();
//        return new ResponseEntity(list, HttpStatus.OK);
//    }
    @GetMapping({"/lista"})
    public List<Portfolio> getPortfolios() {
        return portfolioService.getPortfolios();

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Portfolio> getById(@PathVariable("id") int id) {
        if (!portfolioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Portfolio portfolio = portfolioService.getOne(id).get();
        return new ResponseEntity(portfolio, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Portfolio> getByTitle(@PathVariable("nombre") String nombre) {
        if (!portfolioService.existsByTitle(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Portfolio portfolio = portfolioService.getByTitle(nombre).get();
        return new ResponseEntity(portfolio, HttpStatus.OK);
    }

//    -------------------------------------------
    @PostMapping(value = {"/create"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> save(@RequestPart("portfolio") Portfolio portfolio,
            @RequestPart("imageFile") MultipartFile[] file) {
//        return portfolioService.save(portfolio);
        try {
            Set<ImageModel> images = uploadImage(file);
            portfolio.setDataImage(images);
            portfolioService.save(portfolio);
            return new ResponseEntity(new Mensaje("Portafolio creado"), HttpStatus.OK);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
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

//    -------------------------------------------
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestParam("file") MultipartFile imagen, 
//                                    @RequestParam("title") String title,
//                                    @RequestParam("description") String description,
//                                    Portfolio portfolio){
////            PortfolioDto portfolioDto) {
////        , @RequestParam("file") MultipartFile imagen
//
//          if(title.isEmpty()){
//              return new ResponseEntity(new Mensaje("el Titulo es obligatorio"), HttpStatus.BAD_REQUEST);
//          }
//          if(description.isEmpty()){
//              return new ResponseEntity(new Mensaje("La descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
//          }
//    
////        if (StringUtils.isBlank(portfolioDto.getTitle())) {
////            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
////        }
////        if (portfolioDto.getDescription() == null || portfolioDto.getDescription() == null) {
////            return new ResponseEntity(new Mensaje("debe ingresar una descripcion"), HttpStatus.BAD_REQUEST);
////        }
////        if (portfolioService.existsByTitle(portfolioDto.getTitle())) {
////            return new ResponseEntity(new Mensaje("ese titulo ya existe"), HttpStatus.BAD_REQUEST);
////        }
//
//        if (!imagen.isEmpty()) {
//            Path directorioImagenes = Paths.get("src//main//resources//static/images");
//            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
//            
//
//            try {
//                byte[] bytesImg = imagen.getBytes();
//                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
//                                
//                portfolio.setImagen(bytesImg);
//
//                Files.write(rutaCompleta, bytesImg);
//                
//
//            } catch (IOException e) {
//
//            }
//        }
//
//        portfolio.setTitle(title);
//        portfolio.setDescription(description);
//
////        portfolio.setHabilidad(portfolioDto.getHabilidad());
//
//        portfolioService.save(portfolio);
//        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
//    }
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody PortfolioDto portfolioDto) {
//        if (!portfolioService.existsById(id)) {
//            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
//        }
//        if (portfolioService.existsByTitle(portfolioDto.getTitle()) && portfolioService.getByTitle(portfolioDto.getTitle()).get().getId() != id) {
//            return new ResponseEntity(new Mensaje("ese titulo ya existe"), HttpStatus.BAD_REQUEST);
//        }
//        if (StringUtils.isBlank(portfolioDto.getTitle())) {
//            return new ResponseEntity(new Mensaje("el titutlo es obligatorio"), HttpStatus.BAD_REQUEST);
//        }
//        if (portfolioDto.getDescription() == null || portfolioDto.getDescription() == null) {
//            return new ResponseEntity(new Mensaje("debe ingresar una descripcion"), HttpStatus.BAD_REQUEST);
//        }
//
//        Portfolio portfolio = portfolioService.getOne(id).get();
//        portfolio.setTitle(portfolioDto.getTitle());
//        portfolio.setHabilidad(portfolioDto.getHabilidad());
//        portfolioService.save(portfolio);
//        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
//    }
//    

   

//    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
//        Set<ImageModel> imageModels = new HashSet<>();
//
//        for (MultipartFile file : multipartFiles) {
//            ImageModel imageModel = new ImageModel(
//                    file.getOriginalFilename(),
//                    file.getContentType(),
//                    file.getBytes()
//            );
//            imageModels.add(imageModel);
//        }
//        return imageModels;
//    }

//    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!portfolioService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        portfolioService.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }

//    ---------
}
