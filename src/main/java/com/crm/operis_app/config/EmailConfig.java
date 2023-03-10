package com.crm.operis_app.config;

import com.crm.operis_app.model.server.ServerConfig;
import com.crm.operis_app.services.server.ServerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Optional;
import java.util.Properties;

@Configuration

public class EmailConfig {
    @Autowired
    ServerConfigService configService;

    @Bean
    public JavaMailSender getJavaMailSender() {
        Optional<ServerConfig> conf=  configService.getServerConfigById(1l);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(conf.get().getSmtp());
        mailSender.setPort( conf.get().getServerPort());
//
        //       mailSender.setUsername(conf.get().getEmail());
        //     mailSender.setPassword(conf.get().getPassword());


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol",conf.get().getTransportProtocol());
//
        props.put("mail.smtp.ssl.enable", conf.get().getSsl());
        // props.put("mail.smtp.auth", "true");
        // props.put("mail.smtp.starttls.enable", "true");
        // props.put("mail.debug", "false");



        return mailSender;
    }

    //mailSender.setHost(conf.get().getSmtp());
    //mailSender.setPort( conf.get().getServerPort());

/*
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
       mailSender.setUsername("ayariachref1995@gmail.com");
       mailSender.setPassword("*****");
*/

    //Properties props = mailSender.getJavaMailProperties();
    //props.put("mail.transport.protocol",conf.get().getTransportProtocol());
/*
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");
*/

/*
    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("mail/MailMessages");
        return messageSource;
    }
*/


/*
    @Bean
    public TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // Resolver for TEXT emails
        templateEngine.addTemplateResolver(textTemplateResolver());
        // Resolver for HTML emails (except the editable one)
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        // Resolver for HTML editable emails (which will be treated as a String)
        templateEngine.addTemplateResolver(stringTemplateResolver());
        // Message source, internationalization specific to emails
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }
    private ITemplateResolver textTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(1));
        templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".txt");
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(2));
        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
        templateResolver.setPrefix("/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private ITemplateResolver stringTemplateResolver() {
        final StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(3));
        // No resolvable pattern, will simply process as a String template everything not previously matched
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
*/


}