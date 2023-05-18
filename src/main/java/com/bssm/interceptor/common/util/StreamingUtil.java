package com.bssm.interceptor.common.util;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StreamingUtil {

    private static final String PATH = "/Users/min/projects/music-streaming-app/file/music/";

    private static final long CHUNK_SIZE = 1024 * 1024;

    private static final ConcurrentHashMap<String, Resource> RESOURCE_CACHE = new ConcurrentHashMap<>();

    public static ResourceRegion streamMp3(String uid, HttpHeaders headers) {
        Resource resource = getResource(uid);
        long contentLength = getContentLength(uid);
        long start = 0;
        long rangeLength = Long.min(CHUNK_SIZE, contentLength);

        // httpRange 설정
        if (headers.getRange().size() > 0) {
            HttpRange httpRange = headers.getRange().get(0);
            start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);

            rangeLength = Long.min(CHUNK_SIZE, end - start + 1);
        }

        return new ResourceRegion(resource, start, rangeLength);
    }

    public static String getPath() {
        return PATH;
    }

    public static Resource getResource(String uid) {
        Resource resource = RESOURCE_CACHE.get(uid);
        if (resource == null) {
            resource = new FileSystemResource(PATH + uid + ".mp3");
            RESOURCE_CACHE.put(uid, resource);
        }
        return resource;
    }

    public static Long getContentLength(String uid) {
        try {
            Resource resource = getResource(uid);
            return resource.contentLength();
        } catch (IOException e) {
            return 0L;
        }
    }

}