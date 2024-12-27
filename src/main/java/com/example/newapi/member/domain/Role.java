package com.example.newapi.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST"), //Oauth 첫 로그인시
    USER("ROLE_USER"); //자체 회원가입
    private final String key;
}
