package com.paprika.domain.mypage.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 리뷰 작성 요청 DTO
 * 담당: E - 장인호
 */
@Getter
public class ReviewCreateRequest {

    @NotNull
    private Long transactionId;

    @NotNull
    private Long revieweeId;

    private String content;

    @NotNull
    @Min(-1) @Max(1)
    private Integer mannerScore; // -1(비매너), 0(보통), +1(매너)
}
