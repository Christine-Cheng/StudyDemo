package com.test.string;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-01-24 10:48:19
 **/
public class TestString {
    public static void main(String[] args) {
        //test1
        // TheStr theStr =new TheStr();
        // theStr.setId(1000);
        // theStr.setName("法外狂徒张三");
        //
        // System.out.println("Age: "+ StringUtils.isBlank(String.valueOf(theStr.getAge())));
        
        
        //test2
        String blank = "     ";
        System.out.println(StringUtils.isBlank(blank.trim()));
        
        
        //test3
        
        
    }
    
    public static class strVO {
    
    }
}

@Data
class TheStr {
    private Integer id;
    private String name;
    private Integer age;
}
