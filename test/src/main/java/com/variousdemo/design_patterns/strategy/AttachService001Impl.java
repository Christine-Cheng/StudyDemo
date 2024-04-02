package com.variousdemo.design_patterns.strategy;

import org.springframework.stereotype.Service;

/**
 * @Describe: 图传样例001
 * @Author Happy
 * @Create 2024-03-28 15:21:56
 **/
@Service
public class AttachService001Impl extends AbstractAttachService {
    
    /**
     * 重写support方法判断是否支持该业务
     *
     * @param attachParamVO
     *
     * @return
     */
    @Override
    public boolean support(AttachParamVO attachParamVO) {
        return BusinessScene.SCENE_001.getCode().equals(attachParamVO.getScene())
                && BusinessType.TYPE_001.getCode().equals(attachParamVO.getServerType());
    }
}
