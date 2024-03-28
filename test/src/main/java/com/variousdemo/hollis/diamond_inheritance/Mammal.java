package com.variousdemo.hollis.diamond_inheritance;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-03-27 10:56:51
 **/
public interface Mammal {
    
    public default void eat() {
        System.out.println("Mammal Is Eating");
    }
    
    public default void sleep() {
        System.out.println("Mammal Is Sleeping");
    }
}
