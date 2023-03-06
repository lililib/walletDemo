package com.wallet.wallet2demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wallet.wallet2demo.common.BaseDomain;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("t_log")
public class Log extends BaseDomain {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String userId;

    //变动详情
    private String detail;

}
