package com.variousdemo.design_patterns.strategy;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Describe: 业务类型场景枚举
 * @Author Happy
 * @Create 2024-04-02 11:01:04
 **/
public enum BusinessType {
    TYPE_001("001", "业务类型1"),
    TYPE_002("002", "业务类型2"),
    TYPE_003("003", "业务类型3"),
    ;
    
    private String code;
    private String info;
    
    BusinessType(String code, String info) {
        this.code = code;
        this.info = info;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getInfo() {
        return info;
    }
    
    public static Optional<BusinessType> getByCodeForeach(String code) {
        for (BusinessType businessType : BusinessType.values()) {
            if (businessType.getCode().equals(code)) {
                return Optional.of(businessType);
            }
        }
        return Optional.empty();
    }
    
    public static Optional<BusinessType> getByCodeStream(String code) {
        return Arrays.stream(values()).filter(businessType -> businessType.getCode().equals(code)).findFirst();
    }
}
