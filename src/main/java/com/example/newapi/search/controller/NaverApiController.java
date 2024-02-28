package com.example.newapi.search.controller;

import com.example.newapi.search.domain.SearchResultDto;
import com.example.newapi.search.service.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NaverApiController {

    private final SearchService searchService;

    private final ObjectMapper objectMapper;

    @RequestMapping(value = "naver/api")
    public String getNaverBlog(HttpServletRequest request, Model model) {
        try {
            String keyword = "선거";
            
            ResponseEntity<String> result = searchService.SearchApiCall(keyword);
            String jsonString = result.getBody();

            SearchResultDto searchResultsDTO = objectMapper.readValue(jsonString, SearchResultDto.class);
            model.addAttribute("searchResultsDTO" , searchResultsDTO);

        } catch (Exception e) {

        }
        return "user/mypage/mypage";
    }
}
