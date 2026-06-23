package com.paprika.domain.transaction.dto;

import com.paprika.domain.transaction.entity.Transaction;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 거래 응답 DTO
 * 담당: D - 이동준
 */
@Getter
@Builder
public class TransactionResponse {

    private Long id;
    private Long productId;
    private Long buyerId;
    private Long sellerId;
    private String type;
    private String status;
    private BigDecimal amount;
    private String meetingLocation;
    private LocalDateTime meetingTime;
    private String trackingNumber;
    private LocalDateTime createdAt;

    public static TransactionResponse from(Transaction tx) {
        return TransactionResponse.builder()
            .id(tx.getId())
            .productId(tx.getProductId())
            .buyerId(tx.getBuyerId())
            .sellerId(tx.getSellerId())
            .type(tx.getType().name())
            .status(tx.getStatus().name())
            .amount(tx.getAmount())
            .meetingLocation(tx.getMeetingLocation())
            .meetingTime(tx.getMeetingTime())
            .trackingNumber(tx.getTrackingNumber())
            .createdAt(tx.getCreatedAt())
            .build();
    }
}
