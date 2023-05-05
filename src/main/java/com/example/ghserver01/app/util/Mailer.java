package com.example.ghserver01.app.util;

import com.example.ghserver01.app.util.Value.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Mailer {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String username;


    public void sendMail(String mail, String code) {

        SimpleMailMessage simpleMail = new SimpleMailMessage();

        try {
            simpleMail.setFrom(username);
            simpleMail.setTo(mail);
            simpleMail.setSubject(Constants.SUBJECT);
            simpleMail.setText(code);

            mailSender.send(simpleMail);

        } catch (MailSendException exc) {
            Map<Object, Exception> exceptionsByMails = exc.getFailedMessages();
        }
    }
}
