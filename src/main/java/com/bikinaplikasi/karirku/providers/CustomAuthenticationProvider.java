package com.bikinaplikasi.karirku.providers;

import com.bikinaplikasi.karirku.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserRepository userRepository;

    boolean shouldAuthenticateAgainstThirdPartySystem = true;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

                    final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            final UserDetails principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
            return auth;

//        com.bikinaplikasi.karirku.entity.User.User user = userRepository.findUserByEmail(name);
//
//        if (user == null) {
//            return null;
//        }
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        if (passwordEncoder.matches(password, user.getPassword())) {
//
//            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
//            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//            final UserDetails principal = new User(name, password, grantedAuths);
//            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
//            return auth;
//        } else {
//            return null;
//        }
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

