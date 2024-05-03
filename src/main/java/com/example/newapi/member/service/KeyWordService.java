package com.example.newapi.member.service;

import com.example.newapi.member.domain.KeyWord;

import java.security.Key;
import java.util.List;

public interface KeyWordService {

    public List<KeyWord> findKeyword(String userId);

    public void saveKeyword(String userId, String KeyWord);
}
