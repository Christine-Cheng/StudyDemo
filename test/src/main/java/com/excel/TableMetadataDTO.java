package com.excel;

import lombok.Data;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-02-27 10:25:38
 **/
@Data
public class TableMetadataDTO {
    private String tableName;
    private String fieldName;
    private String fieldType;
    private String fieldComment;
    private String tableComment;
}
