package com.kuangstudy.multithreading.KS05ThreadSynchro;

/**
 * @Describe: 不安全的取钱
 * 俩个人去银行取钱,账户
 * @Author Happy
 * @Create 2023/3/26-21:27
 **/
public class UnsafeBank {
    public static void main(String[] args) {
        //账户
        Account1 account1 = new Account1(100, "结婚基金", "100080009999");
        DrawingCash1 you = new DrawingCash1(account1, 50, "你");
        DrawingCash1 girlFriend = new DrawingCash1(account1, 100, "girlFriend");
        
        you.start();
        girlFriend.start();
    }
}

//银行模拟取款
class DrawingCash1 extends Thread {
    private Account1 account1;
    private int drawCash;//取钱
    private int nowCash;//现在的余额
    
    public DrawingCash1(Account1 account1, int drawCash, String name) {
        super(name);
        this.account1 = account1;
        this.drawCash = drawCash;
    }
    
    
    //取钱
    @Override
    public void run() {
        //判断是否有钱
        if (account1.balance - drawCash < 0) {
            System.out.println(Thread.currentThread().getName() + "--->" + "钱不够,去不了");
            return;
        }
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //卡内余额=余额-取走的钱
        account1.balance = account1.balance - drawCash;
        
        //手里的钱
        nowCash = nowCash + drawCash;
        
        System.out.println(account1.name + "余额为:" + account1.balance);
        //Thread.currentThread().getName() = this.getName()
        System.out.println(this.getName() + "手里的钱:" + nowCash);
    }
}

class Account1 {
    int balance;//余额
    String name;//名称
    String accountNum;//账号
    
    public Account1(int balance, String name, String accountNum) {
        this.balance = balance;
        this.name = name;
        this.accountNum = accountNum;
    }
}
