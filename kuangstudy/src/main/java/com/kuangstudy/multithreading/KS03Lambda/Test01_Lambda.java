package com.kuangstudy.multithreading.KS03Lambda;
/**
 * @author Happy
 * @create 2021/9/9-0:05
 **/


/**
 * Lamda表达式
 * u 为什么要使用lambda表达式
 * 避免匿名内部类定义过多
 * 可以让你的代码看起来很简洁
 * 去掉了一堆没有意义的代码，只留下核心的逻辑。
 *
 *
 *
 * u 理解Functional Interface（函数式接口）是学习Java8 lambda表达式的关键所在。
 * u 函数式接口的定义：
 * 任何接口，如果只包含唯一一个抽象方法，那么它就是一个函数式接口。
 * 对于函数式接口，我们可以通过lambda表达式来创建该接口的对象。
 * <p>
 * 推导lambda表达式
 */
public class Test01_Lambda {
    //升级2.静态内部类
    static class Like2 implements ILike {
        
        @Override
        public void lambda() {
            System.out.println("2.I like you lambda!静态内部类");
        }
    }
    
    public static void main(String[] args) {
        //接口+实现类
        ILike like = new Like1();
        like.lambda();
        
        //静态内部内
        like = new Like2();
        like.lambda();
        
        
        //升级3.局部类
        class Like3 implements ILike {
            @Override
            public void lambda() {
                System.out.println("3.I like you lambda!局部类");
            }
        }
        like = new Like3();
        like.lambda();
        
        
        //升级4.匿名内部类,必须借助接口,或者父类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("4.I like you lambda!匿名内部类");
                
            }
        };
        
        like.lambda();
        
        
        //升级5.用lambda简化(JDK1.8)
        like = () -> {
            System.out.println("5.I like you lambda!匿名内部类,用lambda简化");
            
        };
        like.lambda();
        
        
    }
}

//定义一个函数式接口
interface ILike {
    void lambda();
}

//1.一个实现类
class Like1 implements ILike {
    @Override
    public void lambda() {
        System.out.println("1.I like you lambda!实现接口");
    }
}
