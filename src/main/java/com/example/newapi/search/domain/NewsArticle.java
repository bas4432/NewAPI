package com.example.newapi.search.domain;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsArticle {
    private String image;
    private String subject;
    private String url;

}
