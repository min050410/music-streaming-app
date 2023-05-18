package com.bssm.interceptor.common.util;

import com.bssm.interceptor.common.exception.FileException;
import java.io.IOException;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUploadUtil {

    private static final String PATH = "/Users/min/projects/music-streaming-app/file/music/";

    public static String uploadMp3(MultipartFile multipartFile) {
        try {
            String originalFileName = multipartFile.getOriginalFilename();
            if (originalFileName == null) {
                throw new FileException("파일 이름이 올바르지 않습니다.");
            }

            String uid = getUid();
            String saveFileName = getSaveFileName(uid, originalFileName);

            multipartFile.transferTo(new File(PATH + saveFileName));
            return uid;
        } catch (IOException e) {
            throw new FileException("변환하는 중에 오류가 발생하였습니다.");
        }
    }

    public static String getPath() {
        return PATH;
    }

    private static String getUid() {
        String uid = UUID.randomUUID().toString();
        return uid.replaceAll("-", "");
    }

    private static String getSaveFileName(String uid, String originalFilename) {
        int extPosIndex = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(extPosIndex + 1);

        return uid + "." + ext;
    }

}