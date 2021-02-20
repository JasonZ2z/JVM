package example.printOddAndEven;

import java.util.concurrent.TimeUnit;

/**
 * 两个线程交替打印奇偶数
 * @author xzheng
 * @since 1.0
 */
public class PrintOddAndEven3 {
    private static volatile int val = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            while (val < 10) {
                if(val % 2 == 0) {
                    try {
                        TimeUnit.MICROSECONDS.sleep(500);
                        System.out.println(Thread.currentThread().getName() + " " + val++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Even").start();

        new Thread(() -> {
            while (val < 10) {
                if(val % 2 == 1) {
                    try {
                        TimeUnit.MICROSECONDS.sleep(500);
                        System.out.println(Thread.currentThread().getName() + " " + val++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Odd").start();
    }
}