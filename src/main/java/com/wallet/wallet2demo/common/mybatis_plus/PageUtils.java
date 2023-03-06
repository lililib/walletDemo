package com.wallet.wallet2demo.common.mybatis_plus;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darvsum on 2020/3/1.
 */
public class PageUtils {

    /**
     * JPA分页封装
     * @param page
     * @return
     */
    public static Pageable initPage(PageForm page){

        Pageable pageable = null;
        int pageNumber = page.getPageNumber();
        int pageSize = page.getPageSize();
        String sort = page.getSort();
        String order = page.getOrder();

        if(pageNumber<1){
            pageNumber = 1;
        }
        if(pageSize<1){
            pageSize = 10;
        }
        if(pageSize>100){
            pageSize = 100;
        }
        if(sort!=null) {
            Sort.Direction d;
            if(order!=null) {
                d = Sort.Direction.DESC;
            } else {
                d = Sort.Direction.valueOf(order.toUpperCase());
            }
            Sort s = Sort.by(d, sort);
            pageable = PageRequest.of(pageNumber-1, pageSize, s);
        } else {
            pageable = PageRequest.of(pageNumber-1, pageSize);
        }
        return pageable;
    }

    /**
     * Mybatis-Plus分页封装
     * @param page
     * @return
     */
    public static Page initMpPage(PageForm page){

        Page p = null;
        int pageNumber = page.getPageNumber();
        int pageSize = page.getPageSize();
        String sort = page.getSort();
        String order = page.getOrder();

        if(pageNumber<1){
            pageNumber = 1;
        }
        if(pageSize<1){
            pageSize = 10;
        }
        if(pageSize>1000){
            pageSize = 1000;
        }
        if(sort!=null) {
           //***驼峰转下划线
            Boolean isAsc = false;
            if(order!=null) {
                isAsc = false;
            } else {
                if("desc".equals(order.toLowerCase())){
                    isAsc = false;
                } else if("asc".equals(order.toLowerCase())){
                    isAsc = true;
                }
            }
            p = new Page(pageNumber, pageSize);
            if(isAsc){
                p.addOrder(OrderItem.asc(sort));
            } else {
                p.addOrder(OrderItem.desc(sort));
            }

        } else {
            p = new Page(pageNumber, pageSize);
        }
        return p;
    }

    /**
     * List 手动分页
     * @param page
     * @param list
     * @return
     */
    public static List listToPage(PageForm page, List list) {

        int pageNumber = page.getPageNumber() - 1;
        int pageSize = page.getPageSize();

        if(pageNumber<0){
            pageNumber = 0;
        }
        if(pageSize<1){
            pageSize = 10;
        }
        if(pageSize>100){
            pageSize = 100;
        }

        int fromIndex = pageNumber * pageSize;
        int toIndex = pageNumber * pageSize + pageSize;

        if(fromIndex > list.size()){
            return new ArrayList();
        } else if(toIndex >= list.size()) {
            return list.subList(fromIndex, list.size());
        } else {
            return list.subList(fromIndex, toIndex);
        }
    }
}
