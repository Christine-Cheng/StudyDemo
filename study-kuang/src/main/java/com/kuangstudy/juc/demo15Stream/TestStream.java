package com.kuangstudy.juc.demo15Stream;

import java.util.Arrays;
import java.util.List;

/**
 * @Describe: 流式计算
 * @Author Happy
 * @Create 2023/4/23-23:41
 **/
public class TestStream {
    
    /*
     * 题目：请按照给出数据，找出同时满足以下条件的用户
     * 也即以下条件：
     * 1、全部满足偶数ID
     * 2、年龄大于24
     * 3、用户名转为大写
     * 4、用户名字母倒排序
     * 5、只输出一个用户名字 limit
     **/
    
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        
        
        list.stream()
                .filter(user -> {
                    return user.getId() % 2 == 0;
                })
                .filter(user -> {
                    return user.getAge() > 23;
                }).
                map(user -> {
                    return user.getUserName().toUpperCase();
                }).
                sorted((user1, user2) -> {
                    return user2.compareTo(user1); //默认正排序 自己用 compareTo 比较
                }).
                limit(1).
                forEach(System.out::println);
        
        
        /**
         * 1. 首先我们需要将 list 转化为stream流
         * 2. 然后将用户过滤出来，这里用到一个函数式接口Predicate<? super T>，我们可以使用lambda表达式简化
         * 3. 这里面传递的参数，就是Stream流的泛型类型，也就是User，所以，这里可以直接返回用户id为偶数的用户信息;
         * 4. 通过forEach进行遍历，直接简化输出 System.out::println ，等价 System.out.println(u);
         */
        
        
    }
}
