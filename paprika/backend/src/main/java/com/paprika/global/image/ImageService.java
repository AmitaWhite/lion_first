package com.paprika.global.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final Cloudinary cloudinary;

    public String upload(MultipartFile file, String folder) {
        try {
            Map result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                    "folder", "paprika/" + folder,
                    "resource_type", "image"
                )
            );
            return (String) result.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("이미지 업로드 실패", e);
        }
    }

    public List<String> uploadAll(List<MultipartFile> files, String folder) {
        return files.stream()
            .map(file -> upload(file, folder))
            .toList();
    }

    public void delete(String imageUrl) {
        try {
            // URL에서 public_id 추출 (paprika/폴더/파일명)
            String publicId = extractPublicId(imageUrl);
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("이미지 삭제 실패", e);
        }
    }

    // Cloudinary URL 규칙: .../upload/v123456/paprika/folder/filename.jpg
    private String extractPublicId(String url) {
        String[] parts = url.split("/upload/");
        String afterUpload = parts[1]; // v123456/paprika/folder/filename.jpg
        String withoutVersion = afterUpload.replaceFirst("v\\d+/", "");
        // 확장자 제거
        return withoutVersion.replaceAll("\\.[^.]+$", "");
    }
}
