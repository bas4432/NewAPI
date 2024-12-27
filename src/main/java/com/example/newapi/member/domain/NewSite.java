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
    private Long id;
    @Column(name = "site_name", nullable = false)
    private String siteName;
}
