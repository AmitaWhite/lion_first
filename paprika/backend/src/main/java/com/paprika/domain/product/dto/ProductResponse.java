package com.paprika.domain.product.dto;

import com.paprika.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 상품 응답 DTO
 * 담당: B - 백성민
 */
@Getter
@Builder
public class ProductResponse {

    private Long id;
    private Long sellerId;
    private String title;
    private String description;
    private BigDecimal price;
    private String status;
    private String category;
    private String location;
    private Double latitude;
    private Double longitude;
    private List<String> imageUrls;
    private Integer viewCount;
    private LocalDateTime createdAt;

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
            .id(product.getId())
            .sellerId(product.getSellerId())
            .title(product.getTitle())
            .description(product.getDescription())
            .price(product.getPrice())
            .status(product.getStatus().name())
            .category(product.getCategory())
            .location(product.getLocation())
            .latitude(product.getLatitude())
            .longitude(product.getLongitude())
            .imageUrls(product.getImageUrls())
            .viewCount(product.getViewCount())
            .createdAt(product.getCreatedAt())
            .build();
    }
}
