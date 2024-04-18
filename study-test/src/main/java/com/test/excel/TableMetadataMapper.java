package com.test.excel;

import java.util.List;
import java.util.Map;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-02-27 10:22:02
 **/
public interface TableMetadataMapper {
    
    List<TableMetadataDTO> getTableMetadata(Map<String, Object> params);
    
    List<String> getTables(String databaseName);
    
}
