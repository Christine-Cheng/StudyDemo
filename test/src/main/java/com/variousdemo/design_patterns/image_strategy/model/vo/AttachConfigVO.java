package com.variousdemo.design_patterns.image_strategy.model.vo;

import lombok.Data;

/**
 * @Describe: 附件上传配置-响应数据
 * version 1.0
 * @Author Happy
 * @Create 2024-03-28 15:58:13
 **/
@Data
public class AttachConfigVO {
    //附件类型
    private String attachType;
    //附件名称
    private String attachFileName;
    //附件扩展名
    private String attachFileExt;
    //附件数量
    private String attachFileCount;
    //附件是否必传
    private String attachRequired;
    
}
