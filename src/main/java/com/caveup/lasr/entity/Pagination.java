package com.caveup.lasr.entity;

import lombok.Data;

/**
 * @author xueliang.wu
 */
@Data
public class Pagination {

    private int page;
    private int pageSize;
    private int pageCount;
    private int total;

    public Pagination(int page, int pageSize, int pageCount, int total) {
        this.page = page;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.total = total;
    }

    public Pagination(int total) {
        this(1, total, 1, total);
    }

}
