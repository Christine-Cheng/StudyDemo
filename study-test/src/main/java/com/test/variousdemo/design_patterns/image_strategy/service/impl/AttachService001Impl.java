package com.test.variousdemo.design_patterns.image_strategy.service.impl;

import com.test.variousdemo.design_patterns.image_strategy.constant.BusinessSceneEnum;
import com.test.variousdemo.design_patterns.image_strategy.constant.BusinessTypeEnum;
import com.test.variousdemo.design_patterns.image_strategy.model.vo.AttachParamVO;
import com.test.variousdemo.design_patterns.image_strategy.service.AbstractAttachService;
import org.springframework.stereotype.Service;

;

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
        return BusinessSceneEnum.SCENE_001.getCode().equals(attachParamVO.getScene())
                && BusinessTypeEnum.TYPE_001.getCode().equals(attachParamVO.getServerType());
    }
}
