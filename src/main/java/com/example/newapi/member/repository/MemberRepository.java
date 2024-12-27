package com.example.newapi.member.repository;

import com.example.newapi.member.domain.Member;
import com.example.newapi.member.domain.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByRefreshToken(String refreshToken);

    //추가 정보를 입력받아 회원 가입을 진행할 때 소셜 타입, 식별자로 해당 회원을 찾기 위한 메소드 //OAuth2 로그인 구현 시 사용하는 메소드
    Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
