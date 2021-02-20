package example.printOddAndEven;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印奇偶数
 * @author xzheng
 * @since 1.0
 */
public class PrintOddAndEven5 {
    private static int val = 0;
    private static final Lock lock = new ReentrantLock();
    public static void main(String[] args) {

        new Thread(() -> {
            while (val < 10) {
                lock.lock();
                try {
                    if(val % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " " + val++);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "Even").start();

        new Thread(() -> {
            while (val < 10) {
                lock.lock();
                try {
                    if(val % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " " + val++);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "Odd").start();
    }
}