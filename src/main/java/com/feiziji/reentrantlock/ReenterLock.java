package com.feiziji.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by HuangHailiang on 2017/3/11.
 */
public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public void run() {
        for (int j = 0; j < 100000; j++) {
            lock.lock();//使用重入锁保护临界区资源i ， 确保多线程对i 操作的安全性。
            try {
                i++;
            } finally{
                    lock.unlock();//在退出临界区时，必须记得释放锁，否则，其他线程就没有机会再访问临界区了。
                }
            }
        }

        public static void main(String[]args) throws InterruptedException {
            ReenterLock tl = new ReenterLock();
            Thread t1 = new Thread(tl);
            Thread t2 = new Thread(tl);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
           System.out.println(i);

        }
    }