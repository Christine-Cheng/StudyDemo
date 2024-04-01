package com.variousdemo.design_patterns.strategy;

import lombok.Data;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-01 10:02:11
 **/
@Data
public class AttachConfigDTO {
    // 业务类型
    private String type;
    // 场景值
    private String scene;
    // 数据来源
    private String appSource;
    // 当前业务编号
    private String applyCode;
    
}
