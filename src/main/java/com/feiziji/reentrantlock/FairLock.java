package com.feiziji.reentrantlock;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by HuangHailiang on 2017/3/11.
 * 公平锁
 */
public class FairLock implements Runnable {
    public static ReentrantLock fairLock = new ReentrantLock(true);//加上TRUE后就是公平的锁

    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");

            } finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock r1 = new FairLock();
        Thread t1 = new Thread(r1, "Thread_t1");
        Thread t2 = new Thread(r1, "Thread_t2");
        t1.start();
        t2.start();

    }
}
