package com.teamsking.domain.service;

import com.github.pagehelper.Page;
import java.util.List;

public abstract class BaseService {
    protected <S, D> Page<D> convertPage(Page<S> src, List<D> list) {
        Page page = new Page();
        page.setCount(src.isCount());
        page.setCountColumn(src.getCountColumn());
        page.setEndRow(src.getEndRow());
        page.setOrderBy(src.getOrderBy());
        page.setOrderByOnly(src.isOrderByOnly());
        page.setPageNum(src.getPageNum());
        page.setPages(src.getPages());
        page.setPageSize(src.getPageSize());
        page.setPageSizeZero(src.getPageSizeZero());
        page.setReasonable(src.getReasonable());
        page.setTotal(src.getTotal());
        page.setStartRow(src.getStartRow());
        page.addAll(list);
        return page;
    }
}

