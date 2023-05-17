package com.bssm.interceptor.common.util;

import com.bssm.interceptor.common.exception.FileException;
import java.io.IOException;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StreamingUtil {

    private static final String PATH = "/Users/min/projects/music-streaming-app/file/music/maple.mp3";

    private static final String UPLOAD_DIR = "/Users/min/projects/music-streaming-app/file/music/";

    private static final Resource RESOURCE = new FileSystemResource(PATH);

    private static final long CHUNK_SIZE = 1024 * 1024;

    public static ResourceRegion streamMp3(HttpHeaders headers) {
        long contentLength = getContentLength();

        try {
            HttpRange httpRange = headers.getRange().stream().findFirst().get();
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(CHUNK_SIZE, end - start + 1);
            return new ResourceRegion(RESOURCE, start, rangeLength);
        } catch (Exception e) {
            long rangeLength = Long.min(CHUNK_SIZE, contentLength);
            return new ResourceRegion(RESOURCE, 0, rangeLength);
        }
    }

    public static Resource getResource() {
        return RESOURCE;
    }

    public static Long getContentLength() {
        try {
            return RESOURCE.contentLength();
        } catch (IOException e) {
            return 0L;
        }
    }

    public static String getPath() {
        return PATH;
    }

    private static String getSaveFileName(String originalFilename) {
        int extPosIndex = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(extPosIndex + 1);

        return UUID.randomUUID().toString() + "." + ext;
    }

}