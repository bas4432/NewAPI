package com.example.newapi.member.service.Impl;

import com.example.newapi.member.domain.Member;
import com.example.newapi.member.domain.Role;
import com.example.newapi.member.repository.MemberRepository;
import com.example.newapi.member.dto.MemberDto;
import com.example.newapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long join(MemberDto memberDto) throws Exception { //자체 로그인 회원 가입 시 사용하는 회원 가입 API

        if (memberRepository.findByUserId(memberDto.getUserId()).isPresent()) {
            throw new Exception("이미 존재하는 ID입니다.");
        }

        Member member = Member.builder()
                .userId(memberDto.getUserId())
                .email(memberDto.getEmail())
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .role(Role.USER)
                .build();

        return memberRepository.save(member).getUser_seq_no();
    }

    @Override
    public List<MemberDto> findMembers() {
        return null;
    }
}
