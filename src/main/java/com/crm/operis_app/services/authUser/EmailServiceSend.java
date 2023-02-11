package com.crm.operis_app.services.authUser;


import com.crm.operis_app.services.server.ServerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Service
public class EmailServiceSend {
    private final TemplateEngine templateEngine;

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    ServerConfigService configService;
    @Value("classpath:/templates/emails/logo.png")
    private Resource resourceFile;

    @Autowired
    public EmailServiceSend(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(SimpleMailMessage mail) {
        javaMailSender.send(mail);
    }


    public void sendMailTest(String email, String object, String text) throws MessagingException {
        Context context = new Context();
        context.setVariable("text", text);

        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        helper.setFrom(configService.getServerConfigById(1L).get().getEmail());
        helper.setSubject(object);
        helper.setTo(email);

        String process = templateEngine.process("emails/PPAP", context);
        helper.setText(process, true);
        javaMailSender.send(mimeMessage);
        System.out.println(" mail sending confirmation");

    }


}
