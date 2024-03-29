package com.bssm.interceptor.app.web.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListResponse<T> {

    private List<T> list;

    public static <T> ListResponse<T> create(List<T> list) {
        return new ListResponse<>(list);
    }

}
