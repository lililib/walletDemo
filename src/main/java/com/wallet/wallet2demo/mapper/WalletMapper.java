package com.wallet.wallet2demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wallet.wallet2demo.domain.Wallet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WalletMapper extends BaseMapper<Wallet> {
}
