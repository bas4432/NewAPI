package com.example.newapi.security;

import com.example.newapi.member.repository.MemberRepository;
import com.example.newapi.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCustomDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        //즉 내가 만약 데이터베이스를 사용중이라면 이 UserDetailService클래스를 오버라이딩 해서
        //데이터베이스에 있는 유저 아이디를 찾는 로직을 작성을 해서 자격증명을 입증하면된다.

        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 정보를 찾을 수 없습니다. 아이디: " + userId));

        //Authentication토큰 생성
        //SecurtiyContextHolder에 저장

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return User.builder()
                .username(userId)
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }

}
