package com.ics.icsoauth2server.config;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@org.springframework.context.annotation.Configuration
public class FreeMakerConfig {

    public static final Logger LOGGER = LoggerFactory.getLogger(FreeMakerConfig.class);

    @Value("${spring.mail.templates.path}")
    private String mailTemplatesPath;

    @Bean
    public FreeMarkerConfigurer freemarkerClassLoaderConfig() throws TemplateException {
        LOGGER.info("Setting up free-maker template configuration");
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        TemplateLoader templateLoader = new ClassTemplateLoader(this.getClass(), "/" + mailTemplatesPath);
        configuration.setTemplateLoader(templateLoader);
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setConfiguration(configuration);
        return freeMarkerConfigurer;
    }

}
