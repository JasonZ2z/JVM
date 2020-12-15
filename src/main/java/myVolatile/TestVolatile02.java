package myVolatile;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xzheng
 */
public class TestVolatile02 {

    /**
     * 不保证原子性证明
     * i++ 不是原子性操作
     * Java内存模型只保证了基本读取和赋值是原子性操作。
     * 可以通过 synchronized/Lock/AtomicObject 实现原子性
     */
    public static void main(String[] args) {
        MyData02 myData = new MyData02();
        for (int i = 0; i < 10; ++i) {
            new Thread(() -> {
                for (int j = 0; j < 100; ++j) {
                    myData.add();
                    myData.addAto();
                    myData.addLock();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(myData.i);
        System.out.println(myData.j);
        System.out.println(myData.ai);
    }
}

class MyData02 {
    volatile int i = 0;
    int j = 0;
    AtomicInteger ai = new AtomicInteger();
    Lock lock = new ReentrantLock();

    public synchronized void add() {
        i++;
    }

    public void addLock() {
        lock.lock();
        try {
            j++;
        } finally {
            lock.unlock();
        }
    }

    public void addAto() {
        ai.getAndIncrement();
    }
}
