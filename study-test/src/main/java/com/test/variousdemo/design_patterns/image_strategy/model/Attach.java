package com.test.variousdemo.design_patterns.image_strategy.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-03-28 15:41:50
 **/
@Data
public class Attach {
    @NotEmpty
    @Length(min = 1, max = 4, message = "附件类型长度超过限制")
    private String attachFileType;
    @Length(min = 1, max = 32, message = "附件名称长度超过限制")
    private String attachFileName;
    //附件ids
    private List<String> fileIds;
}
