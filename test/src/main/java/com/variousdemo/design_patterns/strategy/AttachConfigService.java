package com.variousdemo.design_patterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-04-01 09:54:16
 **/
@Slf4j
@Service
public class AttachConfigService {
    @Autowired
    private AttachServiceManager attachServiceManager;
    
    @PostMapping(value = "getAttachConfig", name = "获取当前业务配置")
    public void getAttachConfig(AttachConfigDTO attachConfigDTO) {
    
    
    }
}
