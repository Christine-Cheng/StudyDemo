package com.variousdemo.design_patterns.strategy;

import org.springframework.stereotype.Service;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-03-28 15:21:56
 **/
@Service
public class AttachService001Impl extends AbstractAttachService {
    @Override
    public boolean support(AttachParamVO attachParamVO) {
        return false;
    }
}
