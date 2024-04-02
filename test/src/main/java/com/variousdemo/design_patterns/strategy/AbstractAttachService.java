package com.variousdemo.design_patterns.strategy;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Describe: 策略模式 抽象方法 处理通用的 上传业务逻辑、获取附件配置列表
 * @Author Happy
 * @Create 2024-03-28 15:17:54
 **/
@Slf4j
public abstract class AbstractAttachService implements AttachService {
    @Override
    public List<AttachConfigVO> getConfigList(AttachParamVO attachParamVO) {
        log.info("AbstractAttachService getConfigList");
        
        return null;
    }
    
    @Override
    public AttachUploadResultVO upload(AttachParamVO attachParamVO, List<Attach> attachList) {
        return null;
    }
}
