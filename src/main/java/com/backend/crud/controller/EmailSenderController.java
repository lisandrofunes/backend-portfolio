package com.backend.crud.controller;

import com.backend.crud.dto.EmailSenderDto;
import com.backend.crud.dto.Mensaje;
import com.backend.crud.entity.EmailSender;
import com.backend.crud.service.EmailSenderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/correo")
@CrossOrigin(origins = {"https://lisandro-funes.web.app"})
public class EmailSenderController {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private EmailSenderService emailSenderService;
    
    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailSenderDto emailSenderDto){
//      String toEmail, String subject, String body

        if(emailSenderDto.getNombre()==null){
            return new ResponseEntity(new Mensaje("debe ingresar un nombre"), HttpStatus.BAD_REQUEST);
        }
        if(emailSenderDto.getEmail()==null){
            return new ResponseEntity(new Mensaje("debe ingresar un correo electronico"), HttpStatus.BAD_REQUEST);
        }
        if(emailSenderDto.getSubject()==null){
            return new ResponseEntity(new Mensaje("debe ingresar el asunto para el correo electronico"), HttpStatus.BAD_REQUEST);
        }
        if(emailSenderDto.getText()==null){
            return new ResponseEntity(new Mensaje("debe ingresar el texto para el correo electronico"), HttpStatus.BAD_REQUEST);
        }
        
        EmailSender emailSender = new EmailSender(emailSenderDto.getNombre(), emailSenderDto.getEmail(), emailSenderDto.getSubject(), emailSenderDto.getText());
        emailSenderService.save(emailSender);
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lisandrofunes05@gmail.com");
        message.setTo("lisandrofunes05@gmail.com");
        message.setText(emailSenderDto.getText());
        message.setSubject(emailSenderDto.getSubject());
        
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
        
        mailSender.send(message);
        
        return new ResponseEntity(new Mensaje("email enviado"), HttpStatus.OK);
        
    }
}
