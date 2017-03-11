package com.feiziji.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by HuangHailiang on 2017/3/11.
 * await（）方法会使当前线程等待，同时释放当前锁， 当其他线程中使用signal（）或者
 * signaIA II（）方法时，线程会重新获得锁井继续执行。或者当线程被中断时，也能跳出等
 * 待。
 */
public class ReenterLockCondition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public void run() {
        try {
            lock.lock();
            System.out.println("Thread will be awaited");
            condition.await();
            System.out.println("Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition r1 = new ReenterLockCondition();
        Thread t1 = new Thread(r1);
        t1.start();
        Thread.sleep(2000);
        //通知线程进行执行
        lock.lock();//需要重新获得重入锁，否则会出现java.lang.IllegalMonitorStateException错误
        condition.signal();
        lock.unlock();

    }
}
