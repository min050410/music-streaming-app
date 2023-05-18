package com.bssm.interceptor.common.util;

import com.bssm.interceptor.common.exception.FileException;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileValidateUtil {

    public static void mp3ValidationCheck(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new FileException("이미지 파일이 비어있습니다.");
        }
        if (multipartFile.getSize() > 33554432) {

            throw new FileException("곡 파일의 크기가 32MB를 넘습니다.");
        }
        if (!isSupportedContentType(multipartFile.getContentType())) {
            throw new FileException("MP3만 허용됩니다.");
        }
    }

    private static boolean isSupportedContentType(String contentType) {
        return Objects.equals(contentType, "audio/mpeg");
    }
}
