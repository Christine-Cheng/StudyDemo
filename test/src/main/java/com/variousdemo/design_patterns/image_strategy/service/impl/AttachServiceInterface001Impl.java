package com.variousdemo.design_patterns.image_strategy.service.impl;

import com.variousdemo.design_patterns.image_strategy.constant.BusinessSceneEnum;
import com.variousdemo.design_patterns.image_strategy.constant.BusinessTypeEnum;
import com.variousdemo.design_patterns.image_strategy.model.vo.AttachParamVO;
import com.variousdemo.design_patterns.image_strategy.service.AbstractAttachServiceInterface;
import org.springframework.stereotype.Service;

/**
 * @Describe: 图传样例001
 * @Author Happy
 * @Create 2024-03-28 15:21:56
 **/
@Service
public class AttachServiceInterface001Impl extends AbstractAttachServiceInterface {
    
    /**
     * 重写support方法判断是否支持该业务
     *
     * @param attachParamVO
     *
     * @return
     */
    @Override
    public boolean support(AttachParamVO attachParamVO) {
        return BusinessSceneEnum.SCENE_001.getCode().equals(attachParamVO.getScene())
                && BusinessTypeEnum.TYPE_001.getCode().equals(attachParamVO.getServerType());
    }
}
