package com.sidgo.cinerama.model.service;

import com.sidgo.cinerama.model.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String FROM;

    public void sendMail(MailDTO mail, String template, String subject) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();

        context.setVariables(mail.getModel());
        context.setVariable("year", new SimpleDateFormat("yyyy").format(new Date()));
        String html = templateEngine.process(template, context);

        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.addInline("logo", new ClassPathResource("static/images/logotext.png"), "image/png");
        helper.setSubject(subject);
        helper.setFrom(this.FROM, MailDTO.PERSONAL);

        emailSender.send(message);
    }

}
