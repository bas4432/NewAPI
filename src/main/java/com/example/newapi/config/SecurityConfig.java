package com.example.newapi.config;

import com.example.newapi.security.userCustomAuthenticationProvider.UserCustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private UserCustomAuthenticationProvider userCustomAuthenticationProvider;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/font/**");
    }

    @Bean
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
              http
                .csrf().disable()
                .headers()
                .cacheControl().disable()
                .and()
                .authorizeRequests() //페이지에 대한 권한부여 시작 -> antMatchers를 사용하기위한 메소드
//                     .antMatchers("/user/**").authenticated() // 인증이 필요한 URL
                     .antMatchers("/myinfo/**").hasRole("USER")
                     .antMatchers("/user/login").permitAll()
                     .anyRequest().permitAll()
//                     .antMatchers("/**").permitAll()
                      .and()
                        .formLogin()
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/loginProc")
                        .defaultSuccessUrl("/")
                        .and()
                     .authenticationProvider(userCustomAuthenticationProvider);
        return http.build();

    }

}
