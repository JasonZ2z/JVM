package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xzheng
 */
public class MyReentrantLock {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();


        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);
        t3.start();
        t4.start();


    }

}

class Phone implements Runnable {
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t  invoked sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t  ####  invoked sendEmail");
    }

    @Override
    public void run() {
        get();
    }

    Lock lock = new ReentrantLock();

    private void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t  invoked get");
            set();
        } finally {
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t  #### invoked set");
        } finally {
            lock.unlock();
        }
    }
}
