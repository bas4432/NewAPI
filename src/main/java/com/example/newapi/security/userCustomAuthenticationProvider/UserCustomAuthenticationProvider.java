package com.example.newapi.security.userCustomAuthenticationProvider;


import com.example.newapi.security.UserCustomDetailService;
import com.example.newapi.util.EncryptionSHA512;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserCustomAuthenticationProvider implements AuthenticationProvider {

    private final UserCustomDetailService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getPrincipal().toString(); // 입력받은 email
        String password = authentication.getCredentials().toString(); // 입력받은 pw

        String decryptPw = passwordEncoder.encode(password);

        UserDetails userDetails = userService.loadUserByUsername(userId);

        return new UsernamePasswordAuthenticationToken(userDetails,decryptPw,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
