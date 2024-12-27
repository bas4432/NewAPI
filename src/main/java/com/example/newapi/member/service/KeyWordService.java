
package com.example.newapi.member.service;

import com.example.newapi.member.domain.Keyword;
import com.example.newapi.member.domain.NewSite;
import com.example.newapi.member.domain.UserKeywordNews;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.security.Key;
import java.util.List;

public interface KeyWordService {
    public List<Keyword> findKeyword(String userId);

    public List<UserKeywordNews> findUserKeywordNew(String userId);
    public void saveKeyword(String userId, String KeyWord, String newSite) throws JsonProcessingException;
}

