package com.paprika.global.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ImageService {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024L;
    private static final int MAX_FILE_COUNT = 10;
    private static final List<String> ALLOWED_TYPES = List.of("image/jpeg", "image/png", "image/webp", "image/gif");

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
        List<CompletableFuture<String>> futures = files.stream()
                .map(file -> CompletableFuture.supplyAsync(() -> upload(file, folder), uploadExecutor))
                .toList();
        return futures.stream()
                .map(CompletableFuture::join)
                .toList();
    }

    public void delete(String imageUrl) {
        try {
            String publicId = extractPublicId(imageUrl);
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("이미지 삭제 실패", e);
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
    }

    // Cloudinary URL 규칙: .../upload/v123456/paprika/folder/filename.jpg
    private String extractPublicId(String url) {
        String[] parts = url.split("/upload/");
        String afterUpload = parts[1];
        String withoutVersion = afterUpload.replaceFirst("v\\d+/", "");
        return withoutVersion.replaceAll("\\.[^.]+$", "");
    }
}
