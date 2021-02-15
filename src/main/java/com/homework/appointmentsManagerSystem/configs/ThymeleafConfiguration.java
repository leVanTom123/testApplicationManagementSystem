package com.homework.appointmentsManagerSystem.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfiguration {
    @Bean
    @Description("Thymeleaf template resolver serving navigation bar")
    public ClassLoaderTemplateResolver navigationBarTemplateResolver() {
        ClassLoaderTemplateResolver navigationBarTemplateResolver = new ClassLoaderTemplateResolver();
        navigationBarTemplateResolver.setPrefix("template/");
        navigationBarTemplateResolver.setSuffix(".html");
        navigationBarTemplateResolver.setTemplateMode(TemplateMode.HTML);
        navigationBarTemplateResolver.setCharacterEncoding("UTF-8");
        navigationBarTemplateResolver.setOrder(1);
        navigationBarTemplateResolver.setCheckExistence(true);
        return navigationBarTemplateResolver;
    }
}
