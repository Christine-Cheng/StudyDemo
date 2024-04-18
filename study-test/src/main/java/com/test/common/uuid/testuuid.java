package com.test.common.uuid;

import cn.hutool.core.util.IdUtil;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-17 10:34:31
 **/
public class testuuid {
    public static void main(String[] args) {
        String s = IdUtil.fastSimpleUUID();
        System.out.println(s);
        
    }
}
