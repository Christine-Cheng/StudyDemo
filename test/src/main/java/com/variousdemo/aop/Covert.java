package com.variousdemo.aop;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/3/3-17:21
 **/
public interface Covert<PARAM> {
    
    OperateLogDo covert(PARAM param);
}
