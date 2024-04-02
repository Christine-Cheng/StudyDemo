package com.variousdemo.design_patterns.strategy;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Describe: 业务场景值枚举
 * @Author Happy
 * @Create 2024-04-02 10:51:40
 **/
public enum BusinessScene {
    
    SCENE_001("001", "业务场景1"),
    SCENE_002("002", "业务场景2"),
    SCENE_003("003", "业务场景3"),
    ;
    
    
    private String code;
    private String info;
    
    BusinessScene(String code, String info) {
        this.code = code;
        this.info = info;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getInfo() {
        return info;
    }
    
    
    public static Optional<BusinessScene> getByCodeForeach(String code) {
        for (BusinessScene businessScene : BusinessScene.values()) {
            if (businessScene.getCode().equals(code)) {
                return Optional.of(businessScene);
            }
        }
        return Optional.empty();
    }
    
    public static Optional<BusinessScene> getByCodeStream(String code) {
        return Arrays.stream(values()).filter(businessScene -> businessScene.getCode().equals(code)).findFirst();
    }
    
}
