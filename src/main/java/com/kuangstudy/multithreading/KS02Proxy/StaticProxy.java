package com.kuangstudy.multithreading.KS02Proxy;

/**
 * 人生四大喜事
 * 久旱逢甘露
 * 他乡遇故知
 * 洞房花烛夜
 * 金榜题名时
 *
 * @author Happy
 * @create 2021/9/8-23:39
 **/

/**
 * 静态代理类
 * 1.真实对象和代理对象都必须实现同一个接口
 * 2.代理对象要代理真实的角色
 * <p>
 * 优点:
 * 1.代理对象可以做真实对象做不了的操作
 * 2.真实对象专注自身的操作
 */
public class StaticProxy {
    public static void main(String[] args) {
        //真实对象
        You you = new You();
        //代理对象,代理真实对象,并且做一些真实对象做不了的操作
        WeddingCompany weddingCompany = new WeddingCompany(you);
        weddingCompany.HappyMarry();
    }
}


interface Marry {
    void HappyMarry();
}

//真实角色
class You implements Marry {
    
    @Override
    public void HappyMarry() {
        System.out.println("勋哥哥要结婚了");
    }
}


//代理角色
class WeddingCompany implements Marry {
    //代理的真实目标角色
    private Marry target;
    
    public WeddingCompany() {
    }
    
    public WeddingCompany(Marry target) {
        this.target = target;
    }
    
    
    @Override
    public void HappyMarry() {
        before();
        beforeWedding();
        this.target.HappyMarry();//这就是真实对象
        afterWedding();
        after();
    }
    
    private void before() {
        System.out.println("签订婚庆合同");
    }
    
    private void beforeWedding() {
        System.out.println("婚礼前,布置婚礼现场");
    }
    
    private void afterWedding() {
        System.out.println("婚礼后,收婚庆尾款");
    }
    
    private void after() {
        System.out.println("庆典结束,洞房花烛夜");
    }
    
}


