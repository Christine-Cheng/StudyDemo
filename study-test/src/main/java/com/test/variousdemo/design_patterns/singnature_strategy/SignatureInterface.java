package com.test.variousdemo.design_patterns.singnature_strategy;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-02 14:07:18
 **/
public interface SignatureInterface {
    Boolean support(SignatureParamVO signatureParamVO);
    
    SignatureRespDTO execute(SignatureReqDTO reqDTO, SignatureRespDTO respDTO);
}
