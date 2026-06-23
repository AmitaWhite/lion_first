package com.paprika.domain.mypage.controller;

import com.paprika.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 관리자(Admin) 백오피스 컨트롤러
 * 담당: E - 장인호
 *
 * TODO:
 *  - POST   /api/v1/admin/notices             공지사항 작성
 *  - GET    /api/v1/admin/notices             공지사항 목록
 *  - PUT    /api/v1/admin/notices/{id}        공지사항 수정
 *  - DELETE /api/v1/admin/notices/{id}        공지사항 삭제
 *  - GET    /api/v1/admin/categories          카테고리 목록
 *  - POST   /api/v1/admin/categories          카테고리 추가
 *  - PUT    /api/v1/admin/categories/{id}     카테고리 수정
 *  - GET    /api/v1/admin/reports             신고 접수 목록
 *  - PATCH  /api/v1/admin/reports/{id}        신고 처리 (유저 제어 포함)
 *  - PATCH  /api/v1/admin/users/{id}/status   유저 정지/해제
 */
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    // TODO: private final AdminService adminService;

    @GetMapping("/notices")
    public ResponseEntity<ApiResponse<Object>> getNotices() {
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/reports")
    public ResponseEntity<ApiResponse<Object>> getReports() {
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
