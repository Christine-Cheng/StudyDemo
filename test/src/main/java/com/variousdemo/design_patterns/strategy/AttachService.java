package com.variousdemo.design_patterns.strategy;

import java.util.List;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-03-28 15:18:31
 **/
public interface AttachService {
    
    // 获取当前场景下上传文件的配置列表
    List<AttachConfigVO> getConfigList(AttachParamVO attachParamVO);
    
    // 上传文件结果
    AttachUploadResultVO upload(AttachParamVO attachParamVO, List<Attach> attachList);
    
    // 是否支持场景
    boolean support(AttachParamVO attachParamVO);
}
