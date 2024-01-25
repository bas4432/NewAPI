package com.example.newapi.member.controller;


import com.example.newapi.member.dto.MemberDto;
import com.example.newapi.member.repository.MemberRepository;
import com.example.newapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/member/login")
    public String UserPage() {  //로그인
        return "user/login";
    }

    @GetMapping("/mypage/info")
    public String UsermyPage() {
        return "user/mypage/mypage";
    }

    @GetMapping("/admin/login")
    public String adminPage() {
        return "admin/login";
    }

    @GetMapping("/member/join")
    public String joinPage() {
        return "user/join";
    }

    @PostMapping("/member/signup")
    public String join(@RequestBody MemberDto memberDto) {  // 회원 가입 페이지
        Long memberId = memberService.join(memberDto);
        return "index";
    }

}
