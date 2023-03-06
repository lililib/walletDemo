package com.wallet.wallet2demo.common.mybatis_plus;

import lombok.Data;

@Data
public class PageForm {

    private int pageNumber;
    private int pageSize;

    private String sort;

    private String order;
}
