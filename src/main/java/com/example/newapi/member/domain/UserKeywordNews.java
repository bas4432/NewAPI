package com.example.newapi.member.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor // 모든 필드를 초기화하는 생성자 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
@Table(name = "user_keyword_news")
public class UserKeywordNews{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  // 고유 ID

    @Column(name = "user_id", nullable = false)
    private String userId;  // 유저 ID

    @Column(name = "keyword", nullable = false)
    private String keyWord;  // 키워드

    @Column(name = "news_site", nullable = false)
    private String newsSite;  // 뉴스 사이트

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;  // 생성 일시
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
