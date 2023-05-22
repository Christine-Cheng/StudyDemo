package com.kuangstudy.juc.demo15Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/4/23-23:44
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String userName;
    private int age;
    //get、set、有参/无参构造器、toString
}
