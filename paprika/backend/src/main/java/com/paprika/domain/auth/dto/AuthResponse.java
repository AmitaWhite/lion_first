package com.paprika.domain.auth.dto;

import com.paprika.domain.auth.entity.User;
import lombok.Builder;
import lombok.Getter;

/**
 * 로그인/회원가입 응답 DTO
 * 담당: A - 민동현
 */
@Getter
@Builder
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private UserInfo user;

    @Getter
    @Builder
    public static class UserInfo {
        private Long id;
        private String email;
        private String nickname;
        private String profileImageUrl;
        private String role;

        public static UserInfo from(User user) {
            return UserInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .role(user.getRole().name())
                .build();
        }
    }
}
