package com.example.newapi.member.controller;

import com.example.newapi.member.domain.Keyword;
import com.example.newapi.member.domain.UserKeywordNews;
import com.example.newapi.member.dto.MemberDto;
import com.example.newapi.member.service.KeyWordService;
import com.example.newapi.member.service.MemberService;
import com.example.newapi.search.domain.SearchResultDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final KeyWordService keyWordService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    @GetMapping("/jwt/Test")
    public void jwtTest(HttpServletResponse response) throws IOException {
       log.info("jwt인증 성공");

    }
    @GetMapping("/user/login")
    public String UserPage() {  //로그인
        return "user/login";
    }

    @GetMapping("/user/join")
    public String joinPage() {
        return "user/join";
    }

    @PostMapping("/user/join")
    public String join(MemberDto memberDto) {  // 회원 가입 페이지

//        MemberDto memberDto = MemberDto.builder()
//                .userId(userId)
//                .password(passwordEncoder.encode(password))
//                .username(username)
//                .email(email).build();

        try {
            Long memberId = memberService.join(memberDto);
            log.info("memberId ={} " + memberId);

        }catch (Exception e){
            log.error("회원가입 실패={} " + e.getMessage());
        }
        return "redirect:/user/login";
    }

    @GetMapping("/mypage/info")
    public String UsermyPage(HttpServletRequest request, Model model) throws JsonProcessingException {
        log.info("myPage info");

        String userId = (String) request.getSession().getAttribute("userId");

        log.info("userId==={}" + userId);

        List<Keyword> keyWord = keyWordService.findKeyword(userId);

//        if(keyWord.size() > 0){
//            ResponseEntity<String> result = searchService.SearchApiCall(keyWord.get(0).getKeyword());
//            String jsonString = result.getBody();
//
//            SearchResultDto searchResultsDTO = objectMapper.readValue(jsonString, SearchResultDto.class);
//            model.addAttribute("searchResultsDTO" , searchResultsDTO);
//
//        }
        return "user/mypage/mypage";
    }

    @GetMapping("/mypage/keyword/add")
    public String UsermyPageaddform(HttpServletRequest request, Model model) throws JsonProcessingException {
        String userId = (String) request.getSession().getAttribute("userId");
        log.info("userId" + userId);
        model.addAttribute("userId", userId);
        List<UserKeywordNews> UserKeywordNews = keyWordService.findUserKeywordNew(userId);
        model.addAttribute("UserKeywordNews",  UserKeywordNews);
        log.info("myPage info");
        return "user/mypage/mypageadd";
    }

    @PostMapping("/mypage/keyword/add")
    public String UsermyPageaddformReal(@RequestParam("keyword") String keyword, @RequestParam("newsite") String newsite ,HttpServletRequest request, Model model) throws JsonProcessingException {
        log.info("myPage 등록시작");
        log.info("keyword::" + keyword);
        String userId = (String) request.getSession().getAttribute("userId");
        keyWordService.saveKeyword(userId , keyword , newsite);




        return "redirect:/mypage/keyword/add";
    }

    @GetMapping("/mypage/newsite")
    public String userMyPageNewSite(@RequestParam("keyword") String keyword, HttpServletRequest request, Model model){
        String userId = (String) request.getSession().getAttribute("userId");
        return  "/mypage/newSite";
    }

//    @GetMapping("/mypage/new/add")
//    public String newAddGet(@RequestParam("keyword") String keyword, HttpServletRequest request, Model model) throws JsonProcessingException {
//        log.info("myPage 등록시작");
//        log.info("keyword::" + keyword);
//        String userId = (String) request.getSession().getAttribute("userId");
//        keyWordService.saveKeyword(userId , keyword);
//        return "redirect:/mypage/add";
//    }
//
//    @PostMapping("/mypage/new/add")
//    public String newAddPost(@RequestParam("keyword") String keyword, HttpServletRequest request, Model model) throws JsonProcessingException {
//        log.info("myPage 등록시작");
//        log.info("keyword::" + keyword);
//        String userId = (String) request.getSession().getAttribute("userId");
//        keyWordService.saveKeyword(userId , keyword);
//        return "redirect:/mypage/add";
//    }

}
