package com.example.newapi.member.dto;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private String userId;
    private String username;
    private String email;
    private String password;
}
