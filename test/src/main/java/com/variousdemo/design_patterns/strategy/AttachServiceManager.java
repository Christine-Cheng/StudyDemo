package com.variousdemo.design_patterns.strategy;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-03-28 15:19:33
 **/
@Slf4j
@Component
public class AttachServiceManager {
    @Autowired
    private List<AttachService> attachServiceList;
    
    private AttachService getAttachServiceInstance(AttachParamVO attachParamVO) {
        for (AttachService attachService : attachServiceList) {
            if (attachService.support(attachParamVO)) {
                return attachService;
            }
        }
        log.error("AttachServiceManager 当前场景暂时不支持上传附件:[{}]", JSONUtil.toJsonStr(attachParamVO));
        throw new RuntimeException("当前场景暂时不支持");
    }
}
