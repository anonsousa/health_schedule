package br.com.healthcare.schedule.domain.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Async
@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailWelcomeTemplate welcomeTemplate;

    public void sendWelcomeEmail(String to, String user) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, "utf-8");

        String htmlMessage = welcomeTemplate.buildWelcomeEmail(user);

        messageHelper.setFrom("no-reply@healthcare.com");
        messageHelper.setTo(to);
        messageHelper.setSubject("Bem vindo a HealthCare");
        messageHelper.setText(htmlMessage, true);

        mailSender.send(message);

    }







}