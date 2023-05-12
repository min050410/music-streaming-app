package com.bssm.interceptor.app.web.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.Objects;


@Getter
@NoArgsConstructor
public class Pagination {

    private Integer page;
    private Integer size;
    private Long totalCount;
    private Integer totalPages;

    public static Pagination create(Integer page, Integer size) {
        Pagination pagination = new Pagination();
        pagination.page = checkPage(page);
        pagination.size = checkSize(size);
        return pagination;
    }

    private static Integer checkSize(Integer size) {
        if (Objects.isNull(size) || size < 1 || size > 100) {
            return 10;
        }
        return size;
    }

    private static Integer checkPage(Integer page) {
        if (Objects.isNull(page) || page < 1) {
            return 1;
        }
        return page;
    }

    public PageRequest toPageRequest() {
        return PageRequest.of(this.page - 1, this.size);
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
