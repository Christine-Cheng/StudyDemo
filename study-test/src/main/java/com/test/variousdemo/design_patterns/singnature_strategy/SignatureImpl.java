package com.test.variousdemo.design_patterns.singnature_strategy;

import org.springframework.stereotype.Service;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-02 14:08:04
 **/
@Service
public class SignatureImpl extends AbstractSignatureInterface {
    @Override
    public Boolean support(SignatureParamVO signatureParamVO) {
        return signatureParamVO.getSignatureType().equals("01");
    }
    
    
}
