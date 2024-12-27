package com.example.newapi.member.repository;

import com.example.newapi.member.domain.Keyword;
import com.example.newapi.member.domain.UserKeywordNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserKeywordNewsRepository extends JpaRepository<UserKeywordNews, Long> {
    List<UserKeywordNews> findByUserId(String userId);
}
