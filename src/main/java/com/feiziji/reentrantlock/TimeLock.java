package com.feiziji.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by HuangHailiang on 2017/3/11.
 */
public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
                System.out.println("Get lock success");
            }else {
                System.out.println("Get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()) lock.unlock();
        }
    }

    public static void  main(String[] args){
        TimeLock t1 = new TimeLock();
        Thread r1 = new Thread(t1);
        Thread r2 = new Thread(t1);
        r1.start();
        r2.start();

    }
}
