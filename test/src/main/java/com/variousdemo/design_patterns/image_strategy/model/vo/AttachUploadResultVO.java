package com.variousdemo.design_patterns.image_strategy.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-03-28 15:24:33
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class AttachUploadResultVO {
    /**
     * 上传结果
     */
    List<UploadResult> uploadResultList;
    
    
    @Data
    public static class UploadResult {
        //上传状态 0-失败 1-成功
        private String status;
        //上传结果信息
        private String message;
        //本地文件编号
        private String localFileId;
        //文件系统 编号
        private String docSysId;
        //文件系统 文件编号
        private String fid;
        //附件类型
        private String attachFileType;
        //附件名称
        private String attachFileName;
        //附件显示名称
        private String attachDisplayName;
        // 附件大小
        private String attachFileSize;
        // 附件路径
        private String attachFilePath;
        // 附件扩展名
        private String attachFileExt;
        // 附件URL
        private String attachFileUrl;
        
        //文件校验参数
        private String fileMd5;
        private String fileSha1;
        private String fileSha256;
        private String fileSha512;
    }
}
