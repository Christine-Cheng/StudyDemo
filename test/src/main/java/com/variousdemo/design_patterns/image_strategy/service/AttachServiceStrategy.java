package com.variousdemo.design_patterns.image_strategy.service;

import cn.hutool.json.JSONUtil;
import com.variousdemo.design_patterns.image_strategy.model.vo.AttachParamVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Describe: 策略模式 匹配具体的实现类中的业务
 * @Author Happy
 * @Create 2024-03-28 15:19:33
 **/
@Slf4j
@Component
public class AttachServiceStrategy {
    @Autowired
    private List<AttachServiceInterface> attachServiceInterfaceList;
    
    private AttachServiceInterface getAttachServiceInstance(AttachParamVO attachParamVO) {
        for (AttachServiceInterface attachServiceInterface : attachServiceInterfaceList) {
            if (attachServiceInterface.support(attachParamVO)) {
                return attachServiceInterface;
            }
        }
        log.error("AttachServiceManager 当前场景暂时不支持上传附件:[{}]", JSONUtil.toJsonStr(attachParamVO));
        throw new RuntimeException("当前场景暂时不支持");
    }
}
