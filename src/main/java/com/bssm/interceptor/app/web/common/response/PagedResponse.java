package com.bssm.interceptor.app.web.common.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagedResponse<T> {

    private Pagination pagination;

    private List<T> list;

    public static <T> PagedResponse<T> create(Pagination pagination, List<T> list) {
        return new PagedResponse<>(pagination, list);
    }

}
