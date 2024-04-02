package com.variousdemo.design_patterns.image_strategy.service.impl;

import com.variousdemo.design_patterns.image_strategy.constant.BusinessSceneEnum;
import com.variousdemo.design_patterns.image_strategy.constant.BusinessTypeEnum;
import com.variousdemo.design_patterns.image_strategy.model.Attach;
import com.variousdemo.design_patterns.image_strategy.model.vo.AttachConfigVO;
import com.variousdemo.design_patterns.image_strategy.model.vo.AttachParamVO;
import com.variousdemo.design_patterns.image_strategy.model.vo.AttachUploadResultVO;
import com.variousdemo.design_patterns.image_strategy.service.AbstractAttachServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Describe: 图传样例002
 * @Author Happy
 * @Create 2024-04-02 11:06:12
 **/
@Service
public class AttachServiceInterface002Impl extends AbstractAttachServiceInterface {
    
    /**
     * @param attachParamVO
     *
     * @return
     */
    @Override
    public List<AttachConfigVO> getConfigList(AttachParamVO attachParamVO) {
        
        // 获取上传配置
        return null;
    }
    
    /**
     * @param attachParamVO
     * @param attachList
     *
     * @return
     */
    @Override
    public AttachUploadResultVO upload(AttachParamVO attachParamVO, List<Attach> attachList) {
        return null;
    }
    
    
    /**
     * 重写support方法判断是否支持该业务
     *
     * @param attachParamVO
     *
     * @return
     */
    @Override
    public boolean support(AttachParamVO attachParamVO) {
        return BusinessSceneEnum.SCENE_002.getCode().equals(attachParamVO.getScene())
                && BusinessTypeEnum.TYPE_002.getCode().equals(attachParamVO.getServerType());
    }
    
    
    /**
     * 特有的方法
     */
    public void otherMethod() {
        System.out.println("otherMethod");
    }
}
