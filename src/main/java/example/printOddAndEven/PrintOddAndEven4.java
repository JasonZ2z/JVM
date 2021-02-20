package example.printOddAndEven;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两个线程交替打印奇偶数
 * @author xzheng
 * @since 1.0
 */
public class PrintOddAndEven4 {
    private static final AtomicInteger val = new AtomicInteger(0);

    public static void main(String[] args) {

        new Thread(() -> {
            while (val.get() < 10) {
                if(val.get() % 2 == 0) {
                    try {
                        TimeUnit.MICROSECONDS.sleep(500);
                        System.out.println(Thread.currentThread().getName() + " " + val.incrementAndGet());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Odd").start();

        new Thread(() -> {
            while (val.get() < 10) {
                if(val.get() % 2 == 1) {
                    try {
                        TimeUnit.MICROSECONDS.sleep(500);
                        System.out.println(Thread.currentThread().getName() + " " + val.incrementAndGet());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Even").start();
    }
}