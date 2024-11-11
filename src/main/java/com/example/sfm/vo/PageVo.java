package com.example.sfm.vo;


/**
 * 分页查询参数
 */
public class PageVo<T> {
    private int page;
    private int limit;
    private T searchParams;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public T getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(T searchParams) {
        this.searchParams = searchParams;
    }
}
