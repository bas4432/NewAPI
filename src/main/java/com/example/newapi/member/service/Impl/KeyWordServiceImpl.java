package com.example.newapi.member.service.Impl;
import com.example.newapi.member.domain.Keyword;
import com.example.newapi.member.domain.UserKeywordNews;
import com.example.newapi.member.repository.KeyWordRepository;
import com.example.newapi.member.repository.UserKeywordNewsRepository;
import com.example.newapi.member.service.KeyWordService;
import com.example.newapi.search.service.NewsSearchService;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyWordServiceImpl implements KeyWordService {
    private final KeyWordRepository keyWordRepository;
    private final NewsSearchService searchService;
    private final UserKeywordNewsRepository userKeywordNewsRepository;
    @Override
    public List<Keyword> findKeyword(String userId) {
        return keyWordRepository.findByUserId(userId);
    }

    @Override
    public List<UserKeywordNews> findUserKeywordNew(String userId) {
        return userKeywordNewsRepository.findByUserId(userId);
    }

    @Override
    public void saveKeyword(String userId,String keyword, String newsite) throws JsonProcessingException {
        UserKeywordNews userKeywordNews = new UserKeywordNews();
        userKeywordNews.setUserId(userId);
        userKeywordNews.setKeyWord(keyword);
        userKeywordNews.setNewsSite(newsite);
        userKeywordNewsRepository.save(userKeywordNews);

        searchService.SearchAPICall(keyword);
    }
}
