package com.example.newapi.member.service;

import com.example.newapi.member.dto.MemberDto;

import java.util.List;

public interface MemberService {

    Long join(MemberDto memberDto) throws Exception;

    List<MemberDto> findMembers();
}
