package com.medical.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * list分页工具类
 * @Author: JiaJieTang
 * @Date: 2022/8/17 8:52
 * @Description:
 */
public class PageListUtil {
    public static Page<Object> pageList(List list, Integer pageSize, Integer pageIndex){
        Page<Object> page = new Page<>();
        int size = list.size();
        if(pageSize > size) {
            pageSize = size;
        }
        // 求出最大页数，防止currentPage越界
        int maxPage = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;
        if(pageIndex > maxPage) {
            pageIndex = maxPage;
        }
        // 当前页第一条数据的下标
        int curIdx = pageIndex > 1 ? (pageIndex - 1) * pageSize : 0;
        List pageList = new ArrayList();
        // 将当前页的数据放进pageList
        for(int i = 0; i < pageSize && curIdx + i < size; i++) {
            pageList.add(list.get(curIdx + i));
        }
        page.setCurrent(pageIndex).setSize(pageSize).setTotal(list.size()).setRecords(pageList);
        return page;
    }
}
