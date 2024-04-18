package com.test.excel;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-02-27 10:33:35
 **/
@Slf4j
public class TableMetadataDTOExportExcel {
    
    
    @Test
    public void test() {
        // TODO
        // MyBatis配置文件路径
        String resource = "mybatis/mybatis-config.xml";
        String database = "db_ruoyi";
        String fileName = "RuoYiTableMetadata.xlsx";
        String filePath = "C:\\Workspace\\ZTemp\\";
        try {
            exportExcel(fileName, filePath, resource, database);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public void exportExcel(String fileName, String filePath, String resource, String databaseName) throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        // 获取SqlSession
        SqlSession session = sqlSessionFactory.openSession();
        // 实例化Mapper
        TableMetadataMapper tableMetadataMapper = session.getMapper(TableMetadataMapper.class);
        // 调用Mapper中的方法 执行SQL语句
        List<String> tableNameList = tableMetadataMapper.getTables(databaseName);
        //过滤出需要的表名称
        List<String> filterTableNameList = tableNameList.stream().filter(item -> item.startsWith("sys_")).collect(Collectors.toList());
        // TODO  filterTableNameList 中可以加入特定的表
        
        
        Map<String, Object> params = new HashMap<>();
        params.put("databaseName", databaseName);
        params.put("tableNameList", filterTableNameList);
        List<TableMetadataDTO> tableMetadataList = tableMetadataMapper.getTableMetadata(params);
        session.close();
        
        
        Map<String, List<TableMetadataDTO>> columnGroupByTablenameMap = tableMetadataList.stream().collect(Collectors.groupingBy(TableMetadataDTO::getTableName));
        columnGroupByTablenameMap.forEach((tableName, columnList) -> {
            if (CollectionUtil.isNotEmpty(columnList)) {
                System.out.println(tableName + "---" + columnList.get(0).getTableComment());
            }
        });
        
        
        // 创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        columnGroupByTablenameMap.forEach((tableName, columnList) -> {
            if (CollectionUtil.isNotEmpty(columnList)) {
                // 创建工作表
                XSSFSheet sheet = workbook.createSheet(tableName);
                
                // 设置列宽
                sheet.setColumnWidth(0, 20 * 256);
                sheet.setColumnWidth(1, 20 * 256);
                sheet.setColumnWidth(2, 40 * 256);
                sheet.setColumnWidth(3, 30 * 256);
                
                // 标题 设置字体和样式: 微软雅黑_14
                XSSFFont titleFontYaHei14Size = workbook.createFont();
                titleFontYaHei14Size.setFontHeightInPoints((short) 14);
                titleFontYaHei14Size.setBold(true);
                titleFontYaHei14Size.setFontName("微软雅黑");
                titleFontYaHei14Size.setColor(IndexedColors.RED.getIndex()); // Only for the first row
                XSSFCellStyle titleFontYaHei14CellStyle = workbook.createCellStyle();
                titleFontYaHei14CellStyle.setFont(titleFontYaHei14Size);
                
                // 标题 设置字体和样式: 微软雅黑_10
                XSSFFont titleFontYaHei10Size = workbook.createFont();
                titleFontYaHei10Size.setFontHeightInPoints((short) 10);
                titleFontYaHei10Size.setBold(true);
                titleFontYaHei10Size.setFontName("微软雅黑");
                titleFontYaHei10Size.setColor(IndexedColors.BLACK.getIndex());
                XSSFCellStyle titleFontYaHei10CellStyle = workbook.createCellStyle();
                titleFontYaHei10CellStyle.setFont(titleFontYaHei10Size);
                
                // 数据 设置字体和样式: 微软雅黑_10
                XSSFFont dataFontYaHei10Size = workbook.createFont();
                dataFontYaHei10Size.setFontHeightInPoints((short) 10);
                dataFontYaHei10Size.setFontName("微软雅黑");
                dataFontYaHei10Size.setColor(IndexedColors.BLACK.getIndex());
                XSSFCellStyle dataFontYaHei10CellStyle = workbook.createCellStyle();
                dataFontYaHei10CellStyle.setFont(dataFontYaHei10Size);
                
                // 数据 设置字体和样式: Consolas_10
                XSSFFont dataFontConsolas10Size = workbook.createFont();
                dataFontConsolas10Size.setFontHeightInPoints((short) 10);
                dataFontConsolas10Size.setFontName("Consolas");
                dataFontConsolas10Size.setColor(IndexedColors.ROYAL_BLUE.getIndex());
                XSSFCellStyle dataFontConsolas10CellStyle = workbook.createCellStyle();
                dataFontConsolas10CellStyle.setFont(dataFontConsolas10Size);
                
                
                // 创建合并的单元格 (第一行 1-4列)
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
                XSSFRow row1 = sheet.createRow(0);
                XSSFCell cell1 = row1.createCell(0);
                String title = "表名: " + tableName + "(" + columnList.get(0).getTableComment() + ")";
                cell1.setCellValue(title);
                cell1.setCellStyle(titleFontYaHei14CellStyle);
                
                // 创建合并的单元格 (第二行 1-4列)
                sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 3));
                XSSFRow row2 = sheet.createRow(1);
                XSSFCell cell2 = row2.createCell(0);
                cell2.setCellValue("作用: ");
                cell2.setCellStyle(titleFontYaHei10CellStyle);
                
                // 设置标题行样式 (第四行)
                XSSFRow headerRow = sheet.createRow(3);
                String[] titles = {"字段名称", "数据类型(长度)", "字段注释", "字段描述"};
                for (int i = 0; i < titles.length; i++) {
                    XSSFCell cell = headerRow.createCell(i);
                    cell.setCellValue(titles[i]);
                    cell.setCellStyle(titleFontYaHei10CellStyle);
                }
                
                // 写入从第五行开始的数据
                // 填充数据
                int rowIndex = 4;
                for (TableMetadataDTO cmd : columnList) {
                    XSSFRow row = sheet.createRow(rowIndex++);
                    XSSFCell dataCell0 = row.createCell(0);
                    dataCell0.setCellValue(cmd.getFieldName());
                    dataCell0.setCellStyle(dataFontConsolas10CellStyle);
                    
                    XSSFCell dataCell1 = row.createCell(1);
                    dataCell1.setCellValue(cmd.getFieldType());
                    dataCell1.setCellStyle(dataFontConsolas10CellStyle);
                    
                    XSSFCell dataCell2 = row.createCell(2);
                    dataCell2.setCellValue(cmd.getFieldComment());
                    dataCell2.setCellStyle(dataFontYaHei10CellStyle);
                    
                    XSSFCell dataCell3 = row.createCell(3);
                    dataCell3.setCellValue("");
                    dataCell3.setCellStyle(dataFontYaHei10CellStyle);
                }
            }
            
            
        });
        
        try (FileOutputStream outputStream = new FileOutputStream(filePath + fileName)) {
            workbook.write(outputStream);
        }
        
        workbook.close();
    }
}
