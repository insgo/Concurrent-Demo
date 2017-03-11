package com.feiziji.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;
/**
 * Created by HuangHailiang on 2017/3/11.
 */
public class ReentrantLockInterrupt implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public ReentrantLockInterrupt(int lock){
        this.lock= lock;
    }

    public void run() {
        try {
            if(lock ==1){
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){}
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){}
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (lock1.isHeldByCurrentThread())
                lock1.unlock();
            if (lock2.isHeldByCurrentThread())
                lock2.unlock();
            System.out.print(Thread.currentThread().getId()+":线程退出");
        }

    }

    public static void main(String[] args) throws InterruptedException{
        ReentrantLockInterrupt r1 = new ReentrantLockInterrupt(1);
        ReentrantLockInterrupt r2= new ReentrantLockInterrupt(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //中断其中一个线程
        t1.interrupt();

    }
}
