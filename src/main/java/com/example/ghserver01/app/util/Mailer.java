package com.example.ghserver01.app.util;

import com.example.ghserver01.app.util.Value.Constants;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
@AllArgsConstructor
public class Mailer {
    private final SimpleMailMessage simpleMail = new SimpleMailMessage();
    private Constants constants;
    private MailSender mailSender;

    public void sendMail (String mail, String text) {
        simpleMail.setFrom(Constants.MAIL);
        simpleMail.setTo(mail);
        simpleMail.setSubject("code");
        simpleMail.setText(text);
        this.mailSender.send(simpleMail);
    }
}
