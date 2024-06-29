package br.com.healthcare.schedule.infra.notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Async
@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailWelcomeTemplate welcomeTemplate;

    @Autowired MailScheduledAppointment mailScheduledAppointment;

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


    public void sendScheduledAppointment(Long id, String to, String user, String medico, String dataConsulta) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, "utf-8");

        LocalDateTime data = LocalDateTime.parse(dataConsulta);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        String dataFormatted = data.format(formatter);

        String htmlMessage = mailScheduledAppointment.buildAppointmentNotificationEmail(user, medico, dataFormatted);

        messageHelper.setFrom("no-reply@healthcare.com");
        messageHelper.setTo(to);
        messageHelper.setSubject(String.format("Consulta de id: %s agendada com sucesso!", id));
        messageHelper.setText(htmlMessage, true);

        mailSender.send(message);
    }







}
