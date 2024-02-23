package com.example.newapi.member.service.Impl;

import com.example.newapi.member.domain.Member;
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
    public Long join(MemberDto memberDto) {
        Member member = Member.builder()
                .user_id(memberDto.getUserId())
                .email(memberDto.getEmail())
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .build();

        return memberRepository.save(member).getUser_seq_no();
    }

    @Override
    public List<MemberDto> findMembers() {
        return null;
    }
}
