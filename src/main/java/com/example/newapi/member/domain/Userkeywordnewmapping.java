package com.example.newapi.member.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ns_userkeywordnewmapping")
public class Userkeywordnewmapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;

    @ManyToOne
    @JoinColumn(name = "news_site_id", nullable = false)
    private NewSite newsSite;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}