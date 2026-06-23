package com.paprika.domain.transaction.dto;

import com.paprika.domain.transaction.entity.Transaction.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 거래 생성 요청 DTO
 * 담당: D - 이동준
 */
@Getter
public class TransactionCreateRequest {

    @NotNull
    private Long productId;

    @NotNull
    private TransactionType type; // DIRECT or DELIVERY

    @NotNull
    private BigDecimal amount;

    // 직거래 시 입력
    private String meetingLocation;
    private Double meetingLatitude;
    private Double meetingLongitude;
    private LocalDateTime meetingTime;
}
