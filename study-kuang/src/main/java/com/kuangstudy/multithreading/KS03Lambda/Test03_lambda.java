package com.kuangstudy.multithreading.KS03Lambda;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/3/25-0:05
 **/
public class Test03_lambda {
    
    static class Adore2 implements IAdore {
        @Override
        public void adoreLambda(int loveTimes) {
            System.out.println("adore2: say love you " + loveTimes + " 静态类");
        }
    }
    
    
    public static void main(String[] args) {
        //1.实现类
        IAdore adore = new Adore();
        adore.adoreLambda(3000);
        
        //2.静态内部类
        adore = new Adore2();
        adore.adoreLambda(3000);
        
        //3.局部类
        class Adore3 implements IAdore {
            @Override
            public void adoreLambda(int loveTimes) {
                System.out.println("adore3: say love you " + loveTimes + " 局部内部类");
            }
        }
        adore = new Adore3();
        adore.adoreLambda(3000);
        
        //4.匿名内部类
        adore = new IAdore() {
            @Override
            public void adoreLambda(int loveTimes) {
                System.out.println("adore4: say love you " + loveTimes + " 匿名内部类");
            }
        };
        adore.adoreLambda(3000);
        
        
        //5.用lambda简化
        adore = (int loveTimes) -> {
            System.out.println("adore5: say love you " + loveTimes + " lambda简化1");
        };
        adore.adoreLambda(3000);
        
        
        //6.用lambda简化
        adore = (loveTimes) -> {
            System.out.println("adore6: say love you " + loveTimes + " lambda简化2");
        };
        adore.adoreLambda(3000);
        
        //7.用lambda简化
        adore = loveTimes -> {
            System.out.println("adore7: say love you " + loveTimes + " lambda简化3");
        };
        adore.adoreLambda(3000);
    }
    
    
}


interface IAdore {
    void adoreLambda(int loveTimes);
}

class Adore implements IAdore {
    @Override
    public void adoreLambda(int loveTimes) {
        System.out.println("adore1: say love you " + loveTimes);
    }
}
