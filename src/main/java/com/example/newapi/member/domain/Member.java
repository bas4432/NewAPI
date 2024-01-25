package com.example.newapi.member.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ns_member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member{

    @Id
    @Column(name = "user_seq_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_seq_no;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
