package com.paprika.global.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ImageService {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024L;
    private static final int MAX_FILE_COUNT = 10;
    private static final List<String> ALLOWED_TYPES = List.of("image/jpeg", "image/png", "image/webp", "image/gif");

    // 파일 포맷별 매직 바이트
    private static final List<byte[]> MAGIC_BYTES = List.of(
            new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF},       // JPEG
            new byte[]{(byte) 0x89, 0x50, 0x4E, 0x47},               // PNG
            new byte[]{0x52, 0x49, 0x46, 0x46},                       // WEBP (RIFF)
            new byte[]{0x47, 0x49, 0x46, 0x38}                        // GIF8
    );

    private final Cloudinary cloudinary;
    private final ExecutorService uploadExecutor = Executors.newFixedThreadPool(5);

    public ImageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String upload(MultipartFile file, String folder) {
        validateFile(file);
        try {
            Map result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "paprika/" + folder,
                            "resource_type", "image"));
            return (String) result.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("이미지 업로드 실패", e);
        }
    }

    public List<String> uploadAll(List<MultipartFile> files, String folder) {
        if (files.size() > MAX_FILE_COUNT) {
            throw new IllegalArgumentException("이미지는 최대 " + MAX_FILE_COUNT + "개까지 업로드 가능합니다.");
        }

        List<String> uploadedUrls = new ArrayList<>();
        try {
            List<CompletableFuture<String>> futures = files.stream()
                    .map(file -> CompletableFuture.supplyAsync(() -> upload(file, folder), uploadExecutor))
                    .toList();

            for (CompletableFuture<String> future : futures) {
                uploadedUrls.add(future.join());
            }
            return uploadedUrls;

        } catch (Exception e) {
            uploadedUrls.forEach(url -> {
                try { delete(url); } catch (Exception ignored) {}
            });
            throw new RuntimeException("이미지 업로드 중 실패. 업로드된 이미지를 롤백했습니다.", e);
        }
    }

    public void delete(String imageUrl) {
        try {
            String publicId = extractPublicId(imageUrl);
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("이미지 삭제 실패", e);
        }
    }

    @PreDestroy
    public void shutdown() {
        uploadExecutor.shutdown();
        try {
            if (!uploadExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
                uploadExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            uploadExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("빈 파일은 업로드할 수 없습니다.");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("파일 크기는 5MB 이하여야 합니다: " + file.getOriginalFilename());
        }
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new IllegalArgumentException("지원하지 않는 파일 형식입니다: " + file.getContentType());
        }
        validateFileSignature(file);
    }

    private void validateFileSignature(MultipartFile file) {
        try {
            byte[] header = file.getBytes();
            for (byte[] magic : MAGIC_BYTES) {
                if (header.length >= magic.length) {
                    boolean match = true;
                    for (int i = 0; i < magic.length; i++) {
                        if (header[i] != magic[i]) {
                            match = false;
                            break;
                        }
                    }
                    if (match) return;
                }
            }
            throw new IllegalArgumentException("유효하지 않은 이미지 파일입니다: " + file.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException("파일 시그니처 검증 실패", e);
        }
    }

    // Cloudinary URL 규칙: .../upload/v123456/paprika/folder/filename.jpg
    private String extractPublicId(String url) {
        String[] parts = url.split("/upload/");
        String afterUpload = parts[1];
        String withoutVersion = afterUpload.replaceFirst("v\\d+/", "");
        return withoutVersion.replaceAll("\\.[^.]+$", "");
    }
}
