package com.variousdemo.design_patterns.strategy;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Describe:
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
