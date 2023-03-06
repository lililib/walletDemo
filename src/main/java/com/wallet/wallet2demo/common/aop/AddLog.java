package com.wallet.wallet2demo.common.aop;


import com.wallet.wallet2demo.domain.Log;
import com.wallet.wallet2demo.mapper.LogMapper;
import com.wallet.wallet2demo.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.math.BigDecimal;

@Aspect
@Component
public class AddLog {

    @Resource
    private LogMapper logMapper;
    @Resource
    private UserService userService;

    @Pointcut("@annotation(com.wallet.wallet2demo.common.aop.InsertLog)")
    private void pt(){}

    @AfterReturning(value = "pt()",returning = "resStr")
    public void updateNode(JoinPoint joinPoint,String resStr){
        Object[] args = joinPoint.getArgs();
        if(!resStr.contains("成功")){
            return;
        }
        String [] map=new String[]{"消费","退款"};
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        InsertLog annotation = method.getAnnotation(InsertLog.class);
        String nodeId = annotation.value();   //0：消费   1：退款
        StringBuilder builder = new StringBuilder(map[Integer.parseInt(nodeId)]);
        BigDecimal amount =(BigDecimal) args[0];
        builder.append(amount.setScale(2,BigDecimal.ROUND_HALF_UP));
        Log log = new Log();
        log.setDetail(builder.toString()).setUserId(userService.getCurUser().getId());
        logMapper.insert(log);
    }
}
