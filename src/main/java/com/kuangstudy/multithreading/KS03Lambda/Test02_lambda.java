package com.kuangstudy.multithreading.KS03Lambda;

/**
 * @author Happy
 * @create 2021/9/12-23:14
 **/
public class Test02_lambda {
    
    //2.静态内部类
    static class Love2 implements ILove {
        
        @Override
        public void love(String name) {
            System.out.println("2.I love you--->" + name);
        }
    }
    
    public static void main(String[] args) {
        ILove love = new Love1();
        
        //1.实现类
        love.love("ZhuLin,实现ILove");
        
        //2.静态内部类
        love = new Love2();
        love.love("ZhuLin,静态内部类");
        
        //3.局部类
        class Love3 implements ILove {
            @Override
            public void love(String name) {
                System.out.println("3.I love you--->" + name);
            }
        }
        love = new Love3();
        love.love("ZhuLin,局部类");
        
        //4.匿名内部类
        love = new ILove() {
            @Override
            public void love(String name) {
                System.out.println("4.I love you--->" + name);
            }
        };
        love.love("ZhuLin,匿名内部类");
        
        //5.用lambda简化
        love = (String name) -> {
            System.out.println("5.I love you--->" + name);
        };
        love.love("ZhuLin,用lambda简化,有参数类型");
        
        //6.用lambda简化
        love = (name) -> {
            System.out.println("6.I love you--->" + name);
        };
        love.love("ZhuLin,用lambda简化,无参数类型");
        
        //7.用lambda简化
        love = name -> System.out.println("7.I love you--->" + name);
        love.love("ZhuLin,用lambda简化,无参数类型,无花括号");
        
        /*
         * 小结
         * 1.lambda表达式只有在一行代码的的情况下才可以简化为一行,如果又多行,那么就用代码块包裹
         * 2.前提是接口为函数式接口(即是一个方法)
         * 3.有1个或以上的参数的情况下可以去掉参数类型,要去掉都去掉,且1个以上的参数必须加括号
         * */
        
    }
    
}


interface ILove {
    void love(String name);
}

//1.实现ILove
class Love1 implements ILove {
    
    @Override
    public void love(String name) {
        System.out.println("1.I love you--->" + name);
    }
}
