package com.test.image;

import lombok.Data;

import java.util.Date;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/8/28-23:45
 **/
@Data
public class FileDTO {
    private String name;
    private String path;
    private String absolutePath;
    private String suffix;
    private Date createTime;
    private Integer occupySize;
    
}
