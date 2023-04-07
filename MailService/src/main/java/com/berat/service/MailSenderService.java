package com.berat.service;

import com.berat.rabbitmq.model.ApproveStatusMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender mailSender;

    public  void sendMail(ApproveStatusMailModel model){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${mailUsername}");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Activation Code ");
        mailMessage.setText("Your activation code : "+model.getActivationCode());
        mailSender.send(mailMessage);
    }
}
