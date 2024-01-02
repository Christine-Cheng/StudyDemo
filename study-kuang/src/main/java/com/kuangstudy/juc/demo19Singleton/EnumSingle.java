package com.kuangstudy.juc.demo19Singleton;

import java.lang.reflect.Constructor;

/**
 * @Describe: 枚举不能被反射
 * @Author Happy
 * @Create 2023/5/5-14:55
 **/
public enum EnumSingle {
    INSTANCE;
    public EnumSingle getInstance() {
        return INSTANCE;
    }
    
}

class Test {
    
    /**
     * 实验1
     * 反射用无参
     * Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor();
     * <p></p>
     * 结果:
     * Exception in thread "main" java.lang.NoSuchMethodException: com.kuangstudy.juc.demo20Singleton.EnumSingle.<init>()
     * at java.lang.Class.getConstructor0(Class.java:3082)
     * at java.lang.Class.getDeclaredConstructor(Class.java:2178)
     * at com.kuangstudy.juc.demo20Singleton.Test.main(EnumSingle.java:28)
     * <p></p>
     * <p></p>
     * 实验2
     * Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class,int.class);
     * <p></p>
     * 结果2: 通过反编译源码可知,枚举有默认的有参构造,参数是string int
     * 报错表示,枚举不能反射
     * Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
     * at java.lang.reflect.Constructor.newInstance(Constructor.java:417)
     * at com.kuangstudy.juc.demo20Singleton.Test.main(EnumSingle.java:39)
     *
     * @param args
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        EnumSingle instance1 = EnumSingle.INSTANCE;
        //Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(null);//反射用无参
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();
        
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
