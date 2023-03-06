package com.wallet.wallet2demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.wallet2demo.domain.User;
import com.wallet.wallet2demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {


    /**
     * 这里直接假设从请求的token，查缓存查到了对应的用户id
     * @return
     */
    public User getCurUser(){
        User user = new User();
        user.setId("123456");
        return user;
    }
}
