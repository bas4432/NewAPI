package com.example.newapi.config;

import com.example.newapi.security.handler.UserLoginFailHandler;
import com.example.newapi.security.handler.UserLoginSuccessHandler;
import com.example.newapi.security.userCustomAuthenticationProvider.UserCustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //기본 설정 개발자가 바꾸도록
@RequiredArgsConstructor
public class SecurityConfig  {

    private final UserCustomAuthenticationProvider userCustomAuthenticationProvider;
    private final UserLoginSuccessHandler userLoginSuccessHandler;
    private final UserLoginFailHandler userLoginFailHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/font/**"); //해당 요청은 Security 제외
    }

    @Bean
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
              http
                .csrf().disable()// CSRF 보안 기능 비활성화
                .headers()// 캐시 제어 헤더 비활성화
                .cacheControl().disable()
                .and()
                .authorizeHttpRequests() //페이지에 대한 권한부여 시작 -> antMatchers를 사용하기위한 메소드
                     .antMatchers("/mypage/**").hasRole("USER") // "/mypage/**" 경로에 대한 접근 권한 설정: "USER" 역할을 가진 사용자만 접근 허용
                     .antMatchers("/user/login").permitAll()    // "/user/login" 경로에 대한 접근 권한 설정: 모든 사용자에게 접근 허용
                     .anyRequest().permitAll() // 나머지 모든 요청에 대한 접근 권한 설정: 모든 사용자에게 접근 허용
                      .and()
                      .formLogin() // 폼 기반 로그인 설정
                      .loginPage("/user/login") // 사용자 정의 로그인 페이지 경로 설정
                      .loginProcessingUrl("/user/loginProc") // 로그인 처리 URL 설정
                      .usernameParameter("userId")   // 사용자명 매개변수 이름 설정
                      .passwordParameter("password") // 비밀번호 매개변수 이름 설정
                      .failureHandler(userLoginFailHandler)    // 로그인 실패 핸들러 설정
                      .successHandler(userLoginSuccessHandler) // 로그인 성공 핸들러 설정
                      .and()
                      .authenticationProvider(userCustomAuthenticationProvider); // 사용자 정의 인증 프로바이더 설정
        return http.build(); // SecurityFilterChain 반환

    }

    // SecurityContextHolder
    // -

}
