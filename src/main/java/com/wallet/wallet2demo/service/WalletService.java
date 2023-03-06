package com.wallet.wallet2demo.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.wallet2demo.common.aop.InsertLog;
import com.wallet.wallet2demo.common.mybatis_plus.PageForm;
import com.wallet.wallet2demo.domain.User;
import com.wallet.wallet2demo.domain.Wallet;
import com.wallet.wallet2demo.mapper.WalletMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class WalletService extends ServiceImpl<WalletMapper, Wallet> {

    @Resource
    private UserService userService;
    @Resource
    LogService logService;


    public String getWallAmount() {
        User curUser = userService.getCurUser();
        Wallet wallet = this.baseMapper.selectOne(new QueryWrapper<Wallet>().eq("user_id", curUser.getId()).eq("del_flag",0));
        if(wallet==null){
            return  "先在数据库里面插入值";
        }
        return wallet.getAmount().toString();
    }

    /**
     * 消费
     * @param amount
     * @return
     */
    @Transactional
    @InsertLog("0")
    public String consumer100(BigDecimal amount) {
        User curUser = userService.getCurUser();
        Wallet wallet = this.baseMapper.selectOne(new QueryWrapper<Wallet>().eq("user_id", curUser.getId()));
        if(wallet==null){
            return "先在数据库里面插入值";
        }
        if(wallet.getAmount().subtract(amount).compareTo(new BigDecimal("0")) == -1){
            return "余额不足";
        }
        wallet.setAmount(wallet.getAmount().subtract(amount));
        this.baseMapper.updateById(wallet);
        return "消费成功";

    }

    /**
     * 退款
     *
     * 这里应该需要提供交易流水号做校验，就不拓展了
     * @param amount
     * @return
     */
    @Transactional
    @InsertLog("1")
    public String refund(BigDecimal amount) {
        if(checkIsUnVaild(amount,1)){   //这里应该需要提供交易流水号等做校验，就不拓展了
            return "操作不合法";
        }
        User curUser = userService.getCurUser();
        Wallet wallet = this.baseMapper.selectOne(new QueryWrapper<Wallet>().eq("user_id", curUser.getId()));
        if(wallet==null){
            return "先在数据库里面插入值";
        }
        wallet.setAmount(wallet.getAmount().add(amount));
        this.baseMapper.updateById(wallet);
        return "退款成功";

    }


    /**
     * 检查登录用户，操作合法性等
     *
     * @param amount
     * @param type  0: 消费   1：退款
     * @return
     */
    private boolean checkIsUnVaild(BigDecimal amount, int type) {
        if(amount.compareTo(BigDecimal.ZERO) <0){
            return true;
        }
        if(type ==0){
            //余额不足不可消费等
        }
        if(type ==1){
            //没有扣款过的东西不可退款，已收货,流水号校验等等。。。。。
        }
        return false;
    }

    /**
     * 获取余额明细
     * @return
     */
    public Page getAmountDetail(PageForm page) {
       return logService.getAmountDetail(page);
    }

}
