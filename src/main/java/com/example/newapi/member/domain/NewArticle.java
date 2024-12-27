package com.example.newapi.member.domain;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news_articles")
public class NewArticle{
        @Id
        private Long id;
        @Column(nullable = false)
        private String title;
        @Column(nullable = false, columnDefinition = "TEXT")
        private String content;
        private String url;
        @Column(nullable = false)
        private String platform;
        @Column(name = "publication_date")
        private LocalDateTime publicationDate;
        @ManyToOne
        @JoinColumn(name = "keyword_id")
        private Keyword keyword;
    }
