package com.kuangstudy.multithreading.KS05ThreadSynchro;

/**
 * @Describe:
 * @Author Happy
 * @Create 2023/3/26-23:01
 **/
public class SafeBank {
    public static void main(String[] args) {
        //账户
        Account2 account2 = new Account2(1000, "结婚基金", "100080009999");
        DrawingCash2 you = new DrawingCash2(account2, 50, "你");
        DrawingCash2 girlFriend = new DrawingCash2(account2, 100, "girlFriend");
        
        you.start();
        girlFriend.start();
    }
}

//银行模拟取款
class DrawingCash2 extends Thread {
    private Account2 account2;
    private int drawCash;//取钱
    private int nowCash;//现在的余额
    
    public DrawingCash2(Account2 account2, int drawCash, String name) {
        super(name);
        this.account2 = account2;
        this.drawCash = drawCash;
    }
    
    
    //取钱
    /**
     * synchronized默认锁的是this,是对象本身.
     * 此处this是银行,银行是锁锁不住的.要锁的是account
     */
    @Override
    public void run() {
    
        /**
         * 锁的是变化的量,需要增删改的对象.
         */
        synchronized (account2) {//synchronized (this) 锁的是银行
            //判断是否有钱
            if (account2.balance - drawCash < 0) {
                System.out.println(Thread.currentThread().getName() + "--->" + "钱不够,去不了");
                return;
            }
    
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            //卡内余额=余额-取走的钱
            account2.balance = account2.balance - drawCash;
    
            //手里的钱
            nowCash = nowCash + drawCash;
    
            System.out.println(account2.name + "余额为:" + account2.balance);
            //Thread.currentThread().getName() = this.getName()
            System.out.println(this.getName() + "手里的钱:" + nowCash);
        }
    }
}

class Account2 {
    int balance;//余额
    String name;//名称
    String accountNum;//账号
    
    public Account2(int balance, String name, String accountNum) {
        this.balance = balance;
        this.name = name;
        this.accountNum = accountNum;
    }
}
