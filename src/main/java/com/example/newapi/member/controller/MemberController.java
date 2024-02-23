package com.example.newapi.member.controller;


import com.example.newapi.member.dto.MemberDto;
import com.example.newapi.member.repository.MemberRepository;
import com.example.newapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/user/login")
    public String UserPage() {  //로그인
        return "user/login";
    }

    @GetMapping("/mypage/info")
    public String UsermyPage() {
        return "user/mypage/mypage";
    }

    @GetMapping("/user/join")
    public String joinPage() {
        return "user/join";
    }

    @PostMapping("/user/join")
    public String join(@RequestParam("userId") String userId,
                       @RequestParam("password") String password,
                       @RequestParam("username") String username,
                       @RequestParam("email") String email) {  // 회원 가입 페이지

        MemberDto memberDto = MemberDto.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .username(username)
                .email(email).build();

        try {
            Long memberId = memberService.join(memberDto);
            log.info("memberId ={} " + memberId);

        }catch (Exception e){
            log.error("회원가입 실패={} " + e.getMessage());
        }
        return "redirect:/user/login";
    }

}
