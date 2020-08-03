package com.jyami.springsecuritypolling.payload.response;

import lombok.*;
import sun.jvm.hotspot.debugger.Page;

import java.util.Collections;
import java.util.List;

/**
 * Created by jyami on 2020/07/31
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PagedResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public static <T> PagedResponse<Object> getEmptyPages(){
        return PagedResponse.builder()
                .content(Collections.emptyList())
                .page(0)
                .size(0)
                .totalElements(0)
                .totalPages(0)
                .last(true)
                .build();
    }
}
