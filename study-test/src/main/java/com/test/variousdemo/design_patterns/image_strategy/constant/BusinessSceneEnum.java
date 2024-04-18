package com.test.variousdemo.design_patterns.image_strategy.constant;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Describe: 业务场景值枚举
 * @Author Happy
 * @Create 2024-04-02 10:51:40
 **/
public enum BusinessSceneEnum {
    
    SCENE_001("001", "业务场景1"),
    SCENE_002("002", "业务场景2"),
    SCENE_003("003", "业务场景3"),
    ;
    
    
    private String code;
    private String info;
    
    BusinessSceneEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getInfo() {
        return info;
    }
    
    
    public static Optional<BusinessSceneEnum> getByCodeForeach(String code) {
        for (BusinessSceneEnum businessSceneEnum : BusinessSceneEnum.values()) {
            if (businessSceneEnum.getCode().equals(code)) {
                return Optional.of(businessSceneEnum);
            }
        }
        return Optional.empty();
    }
    
    public static Optional<BusinessSceneEnum> getByCodeStream(String code) {
        return Arrays.stream(values()).filter(businessSceneEnum -> businessSceneEnum.getCode().equals(code)).findFirst();
    }
    
}
