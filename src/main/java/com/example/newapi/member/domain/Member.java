package com.example.newapi.member.domain;


import lombok.*;


import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ns_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member{
    @Id
    @Column(name = "user_seq_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_seq_no;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "username")
    private String username;
    @Enumerated(EnumType.STRING) //Enum 타입을 DB에 String으로 저장
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(name = "socialtype")
    private SocialType socialType;
    @Column(name = "refresh_token")
    private String refreshToken; // 리프레시 토큰
    @Column(name = "social_id")
    private String socialId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    public void authorizeUser() {
        this.role = Role.USER;
    }
    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}
