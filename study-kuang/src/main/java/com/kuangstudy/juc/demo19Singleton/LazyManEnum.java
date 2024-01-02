package com.kuangstudy.juc.demo19Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Describe: 解决反射破坏单例模式的终极方案: 利用枚举; 反射不能破坏枚举
 * <p>
 * <p>
 * 源码:Constructor.java的newInstance()方法中
 * if ((clazz.getModifiers() & Modifier.ENUM) != 0)
 * throw new IllegalArgumentException("Cannot reflectively create enum objects");
 * ConstructorAccessor ca = constructorAccessor;   // read volatile
 * 即是枚举不能被反射破坏
 * <p>
 * <p>
 * @Author Happy
 * @Create 2023/5/5-14:50
 **/
public class LazyManEnum {
    
    
    private LazyManEnum() {
    }
    
    
    public static enum SingletonEnum {
        SINGLETON_ENUM;
        private LazyManEnum lazyManEnumInstance = null;
        
        private SingletonEnum() {
            lazyManEnumInstance = new LazyManEnum();
        }
        
        public LazyManEnum getInstance() {
            return lazyManEnumInstance;
        }
    }
}

class LazyManEnumTest {
    
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        
        Constructor<LazyManEnum> lazyManEnumConstructor = LazyManEnum.class.getDeclaredConstructor(null);
        LazyManEnum lazyManEnum = lazyManEnumConstructor.newInstance();
        LazyManEnum lazyManEnum2 = lazyManEnumConstructor.newInstance();
        
        System.out.println(lazyManEnum);
        System.out.println(lazyManEnum2);
        
    }
    
}
