package com.test.variousdemo.design_patterns.singnature_strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @Describe: 抽象类充当模板
 * @Author Happy
 * @Create 2024-04-02 14:07:46
 **/
@Slf4j
public abstract class AbstractSignatureInterface implements SignatureInterface {
    @Override
    public SignatureRespDTO execute(SignatureReqDTO reqDTO, SignatureRespDTO respDTO) {
        //参数校验
        
        
        return null;
    }
    
}
