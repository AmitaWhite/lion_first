package com.paprika.domain.mypage.dto;

import com.paprika.domain.mypage.entity.MyPageUser;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 공개 프로필 응답 DTO (다른 유저가 보는 화면용)
 * 담당: E - 장인호
 *
 * 본인 프로필(ProfileResponse)과 달리 이메일 등 민감 정보는 제외한다.
 */
@Getter
@Builder
public class PublicProfileResponse {

    private Long id;
    private String nickname;
    private String profileImageUrl;
    private LocalDateTime createdAt;

    public static PublicProfileResponse from(MyPageUser user) {
        return PublicProfileResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
