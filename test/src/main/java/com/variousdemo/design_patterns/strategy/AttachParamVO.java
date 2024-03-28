package com.variousdemo.design_patterns.strategy;

import lombok.Data;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-03-28 15:23:17
 **/
@Data
public class AttachParamVO {
    //场景值
    private String scene;
    
    //业务类型
    private String serverType;
    
    // 附件来源
    private String sourceType;
    
    // 业务条码
    private String applyId;
    
    // 是否同步上传
    private Boolean uploadSync = false;
    
    // 操作人id
    private String userId;
}
