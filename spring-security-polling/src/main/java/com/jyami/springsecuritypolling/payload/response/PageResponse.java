package com.jyami.springsecuritypolling.payload.response;

import lombok.*;

import java.util.List;

/**
 * Created by jyami on 2020/07/31
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PageResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
