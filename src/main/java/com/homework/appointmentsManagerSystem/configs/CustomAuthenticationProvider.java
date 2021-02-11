package com.homework.appointmentsManagerSystem.configs;

import com.homework.appointmentsManagerSystem.entities.Specialist;
import com.homework.appointmentsManagerSystem.repositories.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    SpecialistRepository specialistRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Specialist specialist = specialistRepository.findByEmail(username);
        return getPasswordAuthenticationToken(username, password, specialist);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private UsernamePasswordAuthenticationToken getPasswordAuthenticationToken(String username,
                                                                               String password,
                                                                               Specialist specialist) {
        if (nonNull(specialist) && specialist.getPassword().equals(password)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + specialist.getRole()));
            return new UsernamePasswordAuthenticationToken
                    (username, password, grantedAuths);
        } else {
            throw new
                    BadCredentialsException("Please check your E-mail address and password");
        }
    }

}
