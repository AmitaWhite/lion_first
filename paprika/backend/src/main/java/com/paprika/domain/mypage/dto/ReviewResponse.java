package com.paprika.domain.mypage.dto;

import com.paprika.domain.mypage.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 리뷰 응답 DTO
 * 담당: E - 장인호
 */
@Getter
@Builder
public class ReviewResponse {

    private Long id;
    private Long transactionId;
    private Long reviewerId;
    private Long revieweeId;
    private String content;
    private Integer mannerScore;
    private LocalDateTime createdAt;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
            .id(review.getId())
            .transactionId(review.getTransactionId())
            .reviewerId(review.getReviewerId())
            .revieweeId(review.getRevieweeId())
            .content(review.getContent())
            .mannerScore(review.getMannerScore())
            .createdAt(review.getCreatedAt())
            .build();
    }
}
