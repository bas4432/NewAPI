package com.example.newapi.member.service.Impl;

import com.example.newapi.member.domain.KeyWord;
import com.example.newapi.member.repository.KeyWordRepository;
import com.example.newapi.member.service.KeyWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyWordServiceImpl implements KeyWordService {

    private final KeyWordRepository keyWordRepository;

    @Override
    public List<KeyWord> findKeyword(String userId) {
        return keyWordRepository.findByUserId(userId);
    }

    @Override
    public void saveKeyword(String userId,String Keyword) {
        KeyWord keyWord = KeyWord.builder()
                .userId(userId)
                .keyword(Keyword)
                .user_seq_no(6)
                .build();
        keyWordRepository.save(keyWord);
    }
}
