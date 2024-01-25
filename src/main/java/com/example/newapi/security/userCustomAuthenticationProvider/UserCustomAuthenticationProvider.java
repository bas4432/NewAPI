package com.example.newapi.security.userCustomAuthenticationProvider;


import com.example.newapi.security.UserCustomDetailService;
import com.example.newapi.util.EncryptionSHA512;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserCustomAuthenticationProvider implements AuthenticationProvider {

    private final UserCustomDetailService userService;
    private final PasswordEncoder passwordEncoder;

    public UserCustomAuthenticationProvider(UserCustomDetailService userService , PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName(); // 입력받은 email
        String password = authentication.getCredentials().toString(); // 입력받은 pw

        String decryptPw = passwordEncoder.encode(password);

        UserDetails userDetails = userService.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
