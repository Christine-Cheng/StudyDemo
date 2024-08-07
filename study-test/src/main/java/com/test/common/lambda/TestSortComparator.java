package com.test.common.lambda;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 结论:List.sort(Comparator.comparing())在比较的key值相同的情况下会改变原本的顺序
 *
 * @Describe:
 * @Author Happy
 * @Create 2024-03-22 16:10:21
 **/
public class TestSortComparator {
    
    public static void main(String[] args) {
        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog("旺财1", 1, 1));
        dogList.add(new Dog("旺财2", 2, 2));
        dogList.add(new Dog("旺财3", 2, 3));
        dogList.add(new Dog("旺财4", 3, 4));
        dogList.add(new Dog("旺财5", 4, 5));
        System.out.println("排序前");
        dogList.forEach(dog -> {
            System.out.println(dog);
        });
        
        dogList.sort(Comparator.comparing(Dog::getAge, (age1, age2) -> age2 - age1));
        
        System.out.println("排序后");
        dogList.forEach(dog -> {
            System.out.println(dog);
        });
        
        //排序前
        // Dog(name=旺财1, age=1, serialNumber=1)
        // Dog(name=旺财2, age=2, serialNumber=2)
        // Dog(name=旺财3, age=2, serialNumber=3)
        // Dog(name=旺财4, age=3, serialNumber=4)
        // Dog(name=旺财5, age=4, serialNumber=5)
        // 排序后
        // Dog(name=旺财5, age=4, serialNumber=5)
        // Dog(name=旺财4, age=3, serialNumber=4)
        // Dog(name=旺财2, age=2, serialNumber=2)
        // Dog(name=旺财3, age=2, serialNumber=3)
        // Dog(name=旺财1, age=1, serialNumber=1)
        
    }
    
}

@Data
class Dog {
    private String name;
    private int age;
    private int serialNumber;
    
    public Dog(String name, int age, int serialNumber) {
        this.name = name;
        this.age = age;
        this.serialNumber = serialNumber;
    }
    
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
        this.serialNumber = serialNumber;
    }
    
}
