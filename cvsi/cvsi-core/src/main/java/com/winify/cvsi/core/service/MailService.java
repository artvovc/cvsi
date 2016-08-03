package com.winify.cvsi.core.service;

import com.winify.cvsi.core.dto.templates.request.RegistrationClientRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSenderImpl mailSender;

    @Autowired
    public MailService(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(RegistrationClientRequest registrationClientRequest) {
        String htmlMsg = "<h1>Hello " + registrationClientRequest.getUsername() + " !</h1>\n" +
                "<h1><a href=\"http://192.168.3.191:8080/cvsi-server/user/"+
                DigestUtils.md2Hex(registrationClientRequest.getEmail()+
                        registrationClientRequest.getUsername()+
                        registrationClientRequest.getPhone()+
                        registrationClientRequest.getPassword())
                +"\" target=\"_blank\">Activate</a></h1>";
        mailSender.send(mimeMessage -> {
            mimeMessage.setContent(htmlMsg, "text/html");
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(registrationClientRequest.getEmail());
            mimeMessageHelper.setSubject("activate !!!");
        });
    }

}
