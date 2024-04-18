package com.test.image;

import lombok.Data;

import java.util.List;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/8/28-23:50
 **/
@Data
public class FolderDTO {
    private Integer fileNum;
    private Integer folderNum;
    private List<FileDTO> fileDTOList;
}
