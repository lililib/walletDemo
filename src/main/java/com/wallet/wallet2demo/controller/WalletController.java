package com.wallet.wallet2demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wallet.wallet2demo.common.mybatis_plus.PageForm;
import com.wallet.wallet2demo.service.WalletService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Resource
    private WalletService walletService;


    /**
     * 查询余额
     * @return
     */
    @GetMapping(value = "/wallAmount")
    public String getWallAmount(){
        return walletService.getWallAmount();
    }

    /**
     * 消费100元
     */

    @PostMapping(value = "/consumer")
    public String consumer100(){
        return walletService.consumer100(new BigDecimal(100));
    }

    /**
     * 退款20元
     */

    @PostMapping(value = "/refund")
    public String refund20(){
        return walletService.refund(new BigDecimal(20));
    }


    /**
     * 查询明细
     */

    @GetMapping(value = "/amountDetail")
    public Page getAmountDetail(PageForm page){
        return walletService.getAmountDetail(page);
    }


}
