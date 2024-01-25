package com.test.lambda;

import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-01-24 11:21:23
 **/
public class TestSortedAndSetValue {
    public static void main(String[] args) {
        List<Person> people = createPeopleList();
        
        // 使用 Lambda 表达式对年龄字段倒序排序，并同时对序号字段进行赋值
        List<Integer> collect = IntStream.range(0, people.size())
                .boxed()
                .sorted((i, j) -> Integer.compare(people.get(j).getAge(), people.get(i).getAge())).collect(Collectors.toList());
        
        List<Person> personSortedList = people.stream().sorted(Comparator.comparing(Person::getAge).reversed()).collect(Collectors.toList());
        for (int i = 0; i < personSortedList.size(); i++) {
            personSortedList.get(i).setSerialNumber(i + 1);
        }
        
        
        // 打印排序后的列表
        personSortedList.forEach(person -> System.out.println("Name: " + person.getName() + ", Age: " + person.getAge() + ", Serial Number: " + person.getSerialNumber()));
    }
    
    private static List<Person> createPeopleList() {
        // 创建一个示例的 Person 列表
        return List.of(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 22)
                // 添加更多 Person 对象
        );
    }
}

@Data
class Person {
    private String name;
    private int age;
    private int serialNumber;
    
    public Person(String name, int age, int serialNumber) {
        this.name = name;
        this.age = age;
        this.serialNumber = serialNumber;
    }
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.serialNumber = serialNumber;
    }
    
    // 省略构造方法和其他属性的 getter/setter
}
