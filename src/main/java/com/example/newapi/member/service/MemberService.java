package com.example.newapi.member.service;

import com.example.newapi.member.dto.MemberDto;

import java.util.List;

public interface MemberService {

    Long join(MemberDto memberDto);

    List<MemberDto> findMembers();
}
