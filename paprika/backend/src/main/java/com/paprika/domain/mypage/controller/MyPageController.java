package com.paprika.domain.mypage.controller;

import com.paprika.domain.mypage.dto.ProfileResponse;
import com.paprika.domain.mypage.dto.ProfileUpdateRequest;
import com.paprika.domain.mypage.dto.TransactionSummaryResponse;
import com.paprika.domain.mypage.service.MyPageService;
import com.paprika.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.paprika.domain.mypage.dto.TransactionSummaryResponse;
import java.util.List;

/**
 * 마이페이지 컨트롤러
 * 담당: E - 장인호
 *
 * - GET    /api/v1/users/me              내 프로필 조회
 * - PATCH  /api/v1/users/me              프로필 수정
 * - GET    /api/v1/users/me/products     나의 판매 상품 목록 (판매중/예약중/완료)
 * - GET    /api/v1/users/me/wishlist     관심 상품(찜) 목록
 * - POST   /api/v1/users/me/wishlist/{productId}    찜 추가
 * - DELETE /api/v1/users/me/wishlist/{productId}    찜 취소
 * - GET    /api/v1/users/{id}/reviews    유저 리뷰 목록 (매너 온도)
 * - POST   /api/v1/reviews               거래 후기 작성
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    /**
     * 내 프로필 조회
     */
   @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getMyProfile(Authentication authentication) {
        // TODO: JWT 연동 후 아래 주석 해제하고 하드코딩 제거
        // String email = authentication.getName();
        String email = "minsu@paprika.com"; // 임시 테스트용
        return ResponseEntity.ok(myPageService.getMyProfile(email));
    }

    /**
     * 내 프로필 수정 (닉네임, 프로필 이미지)
     */
    @PatchMapping("/me")
    public ResponseEntity<ProfileResponse> updateMyProfile(
            Authentication authentication,
            @RequestBody ProfileUpdateRequest request) {
        // String email = authentication.getName();
        String email = "minsu@paprika.com"; // 임시 테스트용
        return ResponseEntity.ok(myPageService.updateMyProfile(email, request));
    }
    /**
     * 닉네임 중복 확인
     */
    @GetMapping("/me/check-nickname")
    public ResponseEntity<Map<String, Boolean>> checkNickname(
            @RequestParam String nickname,
            Authentication authentication) {
        String email = "minsu@paprika.com"; // 임시 테스트용
        boolean isDuplicate = myPageService.isNicknameDuplicate(nickname, email);
        return ResponseEntity.ok(Map.of("isDuplicate", isDuplicate));
    }

    @GetMapping("/me/products")
    public ResponseEntity<ApiResponse<Object>> getMyProducts(
            @RequestParam(required = false, defaultValue = "ALL") String status
    ) {
        // TODO: 판매중/예약중/완료 필터링 구현
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
    /**
 * 나의 거래 내역 조회 (탭별)
 * tab: all | buy | sell | selling
 */
    @GetMapping("/me/transactions")
    public ResponseEntity<ApiResponse<List<TransactionSummaryResponse>>> getMyTransactions(
            @RequestParam(defaultValue = "all") String tab,
            Authentication authentication) {
        String email = "minsu@paprika.com"; // 임시
        return ResponseEntity.ok(ApiResponse.ok(myPageService.getMyTransactions(email, tab)));
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