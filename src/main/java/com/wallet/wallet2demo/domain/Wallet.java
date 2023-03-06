package com.wallet.wallet2demo.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wallet.wallet2demo.common.BaseDomain;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@TableName("t_wallet")
public class Wallet extends BaseDomain {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String userId;
    //余额
    private BigDecimal amount;
}
