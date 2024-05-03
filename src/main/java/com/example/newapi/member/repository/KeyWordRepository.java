package com.example.newapi.member.repository;

import com.example.newapi.member.domain.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyWordRepository extends JpaRepository<KeyWord, Integer> {

    List<KeyWord> findByUserId(String userId);
}
