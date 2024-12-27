//package com.example.newapi.search.controller;
//
//import com.example.newapi.search.domain.NewsArticle;
//import com.example.newapi.search.domain.SearchResultDto;
//import com.example.newapi.search.service.SearchService;
//import com.example.newapi.search.service.newsCrawlerService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//public class NaverApiController {
//
//    private final newsCrawlerService newsCrawlerService;
//    private final ObjectMapper objectMapper;
//
//    @RequestMapping(value = "naver/api")
//    public String getNaverBlog(HttpServletRequest request, Model model) {
//        try {
//            String keyword = "선거";
//
//            ResponseEntity<String> result = searchService.SearchApiCall(keyword);
//            String jsonString = result.getBody();
//
//            SearchResultDto searchResultsDTO = objectMapper.readValue(jsonString, SearchResultDto.class);
//            model.addAttribute("searchResultsDTO" , searchResultsDTO);
//
//        } catch (Exception e) {
//            log.info("Naver API 응답 오류:" + e.getMessage());
//
//        }
//        return "user/mypage/mypage";
//    }
//
//    @GetMapping("/crawl-news")
//    public String crawlNews(HttpServletRequest request , Model model) {
//        List<NewsArticle> newsArticles = newsCrawlerService.crawlGoogleNews("선거");
//        model.addAttribute("newsArticles", newsArticles);
//        return "news";
//    }
//}
