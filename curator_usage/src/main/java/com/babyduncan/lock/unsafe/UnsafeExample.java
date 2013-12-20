package com.babyduncan.lock.unsafe;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: guohaozhao (guohaozhao116008@sohu-inc.com)
 * Date: 12/20/13 16:51
 */
public class UnsafeExample {

    public static int i___ = 0;


    public static void main(String... args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1000);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i__ = 0; i__ < 1000; i__++) {
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    i___++;
                    countDownLatch.countDown();
                    return null;
                }
            };
            service.submit(task);
        }
        countDownLatch.await();
        System.out.println("result is " + i___);   // result less then 1000 , 988 993 maybe always.

    }

}
