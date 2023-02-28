package com.backend.crud.service;

import com.backend.crud.entity.EmailSender;
import com.backend.crud.repository.EmailSenderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailSenderService {
    
    @Autowired
    EmailSenderRepository emailSenderRepository;

    public void save(EmailSender emailSender){
        emailSenderRepository.save(emailSender);
    }


}
