package com.test.variousdemo.hollis.diamond_inheritance;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-03-27 10:56:20
 **/
public class Cat implements Pet, Mammal {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.eat();
        cat.sleep();
        System.out.println(cat);
    }
    
    @Override
    public void eat() {
        Pet.super.eat();
        Mammal.super.eat();
    }
    
    @Override
    public void sleep() {
        Mammal.super.sleep();
    }
}
