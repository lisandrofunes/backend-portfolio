package com.backend.crud.dto;

public class EmailSenderDto {
    
    private String nombre;
    private String email;
    private String text;
    private String subject;
    
    public EmailSenderDto(String nombre, String email, String text, String subject) {
    this.nombre = nombre;
    this.email = email;
    this.text = text;
    this.subject = subject;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    
}
