package com.example.newapi.member.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ns_keyword")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_seq_no", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_seq_no")
    private Integer user_seq_no;

    @Column(name = "keyword")
    private String keyword;

}
