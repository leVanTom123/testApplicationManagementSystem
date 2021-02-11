package com.homework.appointmentsManagerSystem.configs;

import com.homework.appointmentsManagerSystem.repositories.SpecialistRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(
        jsr250Enabled = true,
        securedEnabled = true,
        prePostEnabled = true)
@EnableWebSecurity
public class LoginSecurity extends WebSecurityConfigurerAdapter {
    private SpecialistRepository specialistRepository;

    public LoginSecurity(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/mvc/specialist/managementPanel*").hasRole("SPECIALIST")
                .antMatchers("/mvc/specialist/cancel").hasRole("SPECIALIST")

                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()

                .and()


                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/mvc/specialist/managementPanel")
                .and()
                .logout()
                .logoutSuccessUrl("/");

    }
}
