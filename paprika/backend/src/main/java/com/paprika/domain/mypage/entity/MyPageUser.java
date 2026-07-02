package com.paprika.domain.mypage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyPageUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Provider provider;

    //유저 공개 프로필(가입일 표시)용. 실제 값은 auth 도메인 User 엔티티가 관리.
    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfileImage(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    //User.Provider와 동일한 값. 마이페이지에서 소셜 로그인 여부만 판별하는 용도.
    public enum Provider { LOCAL, GOOGLE, NAVER, GITHUB }
}