package com.variousdemo.design_patterns.singnature_strategy;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Describe: 策略模式 匹配具体的实现类中的业务
 * @Author Happy
 * @Create 2024-04-02 14:09:35
 **/
@Slf4j
@Component
public class SignatureStrategy {
    
    @Autowired
    private List<SignatureInterface> signatureInterfaceList;
    
    private SignatureInterface getSignatureServiceInstance(SignatureParamVO signatureParamVO) {
        for (SignatureInterface signatureInterface : signatureInterfaceList) {
            if (signatureInterface.support(signatureParamVO)) {
                return signatureInterface;
            }
        }
        log.error("SignatureStrategy 当前场景暂时不支持:[{}]", JSONUtil.toJsonStr(signatureParamVO));
        throw new RuntimeException("当前场景暂时不支持");
    }
    
}
