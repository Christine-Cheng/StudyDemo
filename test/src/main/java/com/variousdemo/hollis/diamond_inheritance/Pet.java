package com.variousdemo.hollis.diamond_inheritance;

/**
 * @Describe:
 * @Author Happy
 * @Create 2024-03-27 10:56:35
 **/
public interface Pet {
    
    public default void eat() {
        System.out.println("Pet Is Eating");
    }
}
