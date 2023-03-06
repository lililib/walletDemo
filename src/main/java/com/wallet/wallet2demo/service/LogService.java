package com.wallet.wallet2demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.wallet2demo.common.mybatis_plus.PageForm;
import com.wallet.wallet2demo.common.mybatis_plus.PageUtils;
import com.wallet.wallet2demo.domain.Log;
import com.wallet.wallet2demo.domain.User;
import com.wallet.wallet2demo.mapper.LogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogService extends ServiceImpl<LogMapper, Log>  {

    @Resource
    private UserService userService;

    /**
     * 查询用户余额详情
     * @return
     */
    public Page getAmountDetail(PageForm page) {
        User curUser = userService.getCurUser();
        Page page1 = this.page(PageUtils.initMpPage(page), new QueryWrapper<Log>().eq("user_id", curUser.getId()).eq("del_flag", 0).orderByDesc("create_time"));
        return page1;
    }
}
