package com.example.newapi.member.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ns_new_site")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_site_id", nullable = false)
    private Long newsiteId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_seq_no")
    private Integer user_seq_no;

    @Column(name = "keyword")
    private String keyword;

}
