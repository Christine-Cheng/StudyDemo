package com.test.variousdemo.design_patterns.image_strategy.constant;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Describe: 业务类型场景枚举
 * @Author Happy
 * @Create 2024-04-02 11:01:04
 **/
public enum BusinessTypeEnum {
    TYPE_001("001", "业务类型1"),
    TYPE_002("002", "业务类型2"),
    TYPE_003("003", "业务类型3"),
    ;
    
    private String code;
    private String info;
    
    BusinessTypeEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getInfo() {
        return info;
    }
    
    public static Optional<BusinessTypeEnum> getByCodeForeach(String code) {
        for (BusinessTypeEnum businessTypeEnum : BusinessTypeEnum.values()) {
            if (businessTypeEnum.getCode().equals(code)) {
                return Optional.of(businessTypeEnum);
            }
        }
        return Optional.empty();
    }
    
    public static Optional<BusinessTypeEnum> getByCodeStream(String code) {
        return Arrays.stream(values()).filter(businessTypeEnum -> businessTypeEnum.getCode().equals(code)).findFirst();
    }
}
