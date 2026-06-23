package com.paprika.domain.mypage.controller;

import com.paprika.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 마이페이지 컨트롤러
 * 담당: E - 장인호
 *
 * TODO:
 *  - GET    /api/v1/users/me              내 프로필 조회
 *  - PATCH  /api/v1/users/me              프로필 수정
 *  - GET    /api/v1/users/me/products     나의 판매 상품 목록 (판매중/예약중/완료)
 *  - GET    /api/v1/users/me/wishlist     관심 상품(찜) 목록
 *  - POST   /api/v1/users/me/wishlist/{productId}    찜 추가
 *  - DELETE /api/v1/users/me/wishlist/{productId}    찜 취소
 *  - GET    /api/v1/users/{id}/reviews    유저 리뷰 목록 (매너 온도)
 *  - POST   /api/v1/reviews               거래 후기 작성
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MyPageController {

    // TODO: private final MyPageService myPageService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Object>> getMyProfile() {
        // TODO: 구현 (SecurityContext에서 현재 유저 추출)
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @PatchMapping("/me")
    public ResponseEntity<ApiResponse<Object>> updateMyProfile(
            /* @Valid @RequestBody ProfileUpdateRequest request */
    ) {
        // TODO: 구현
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/me/products")
    public ResponseEntity<ApiResponse<Object>> getMyProducts(
            @RequestParam(required = false, defaultValue = "ALL") String status
    ) {
        // TODO: 판매중/예약중/완료 필터링 구현
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/me/wishlist")
    public ResponseEntity<ApiResponse<Object>> getWishList() {
        // TODO: 구현
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @PostMapping("/me/wishlist/{productId}")
    public ResponseEntity<ApiResponse<Void>> addWish(@PathVariable Long productId) {
        // TODO: 구현
        return ResponseEntity.ok(ApiResponse.ok("관심 상품에 추가되었습니다.", null));
    }

    @DeleteMapping("/me/wishlist/{productId}")
    public ResponseEntity<ApiResponse<Void>> removeWish(@PathVariable Long productId) {
        // TODO: 구현
        return ResponseEntity.ok(ApiResponse.ok("관심 상품에서 제거되었습니다.", null));
    }
}
