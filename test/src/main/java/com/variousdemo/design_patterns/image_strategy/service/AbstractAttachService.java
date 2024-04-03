package com.variousdemo.design_patterns.image_strategy.service;

import com.variousdemo.design_patterns.image_strategy.model.Attach;
import com.variousdemo.design_patterns.image_strategy.model.vo.AttachConfigVO;
import com.variousdemo.design_patterns.image_strategy.model.vo.AttachParamVO;
import com.variousdemo.design_patterns.image_strategy.model.vo.AttachUploadResultVO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Describe: 抽象类做模板方法 处理通用的 上传业务逻辑、获取附件配置列表
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
