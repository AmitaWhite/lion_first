package com.paprika.domain.mypage.dto;

import com.paprika.domain.auth.entity.User;
import lombok.Builder;
import lombok.Getter;

/**
 * 프로필 조회 응답 DTO
 * 담당: E - 장인호
 */
@Getter
@Builder
public class ProfileResponse {
    private Long id;
    private String email;
    private String nickname;
    private String profileImageUrl;
    private Double trustScore;

    public static ProfileResponse from(User user) {
        return ProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .trustScore(user.getTrustScore())
                .build();
    }
}