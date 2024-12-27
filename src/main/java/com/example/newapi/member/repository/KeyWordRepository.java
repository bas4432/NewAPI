package com.example.newapi.member.repository;


import com.example.newapi.member.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyWordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findByUserId(String userId);
}
