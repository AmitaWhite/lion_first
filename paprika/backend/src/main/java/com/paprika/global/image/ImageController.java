package com.paprika.global.image;

import com.paprika.global.response.ApiResponse;
import com.paprika.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 이미지 업로드 컨트롤러 (Cloudinary)
 *
 * POST /api/v1/images/upload        단건 업로드
 * POST /api/v1/images/upload/batch  다건 업로드
 *
 * folder 파라미터: products / profiles (기본값: products)
 * 응답: Cloudinary secure_url 문자열
 *
 * 썸네일 사용법 (프론트에서 URL 파라미터만 추가):
 *   원본:     https://res.cloudinary.com/.../image/upload/paprika/products/xxx.jpg
 *   썸네일:   .../image/upload/w_300,h_300,c_fill/paprika/products/xxx.jpg
 *   최적화:   .../image/upload/f_auto,q_auto/paprika/products/xxx.jpg
 */
@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>> upload(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam MultipartFile file,
            @RequestParam(defaultValue = "products") String folder
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String url = imageService.upload(file, folder);
        return ResponseEntity.ok(ApiResponse.ok(url));
    }

    @PostMapping("/upload/batch")
    public ResponseEntity<ApiResponse<List<String>>> uploadBatch(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam List<MultipartFile> files,
            @RequestParam(defaultValue = "products") String folder
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<String> urls = imageService.uploadAll(files, folder);
        return ResponseEntity.ok(ApiResponse.ok(urls));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(@RequestParam String imageUrl) {
        imageService.delete(imageUrl);
        return ResponseEntity.ok(ApiResponse.ok("이미지가 삭제되었습니다.", null));
    }
}
