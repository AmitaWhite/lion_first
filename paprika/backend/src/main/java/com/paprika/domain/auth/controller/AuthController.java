package com.paprika.domain.auth.controller;

import com.paprika.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 인증 컨트롤러
 * 담당: A - 민동현
 *
 * TODO:
 *  - 회원가입 API: POST /api/v1/auth/signup
 *  - 로그인 API:   POST /api/v1/auth/login
 *  - 토큰 재발급:  POST /api/v1/auth/reissue
 *  - 로그아웃:     POST /api/v1/auth/logout
 *  - 내 정보 조회: GET  /api/v1/auth/me
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    // TODO: private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(/* @Valid @RequestBody SignupRequest request */) {
        // TODO: 구현
        return ResponseEntity.ok(ApiResponse.ok("회원가입 성공", null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(/* @Valid @RequestBody LoginRequest request */) {
        // TODO: 구현
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<Object>> reissue(/* @RequestHeader("Refresh-Token") String refreshToken */) {
        // TODO: 구현
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {
        // TODO: 구현
        return ResponseEntity.ok(ApiResponse.ok("로그아웃 성공", null));
    }
}
