package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * @author xzheng
 */
public class SpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        new Thread(() -> {
            spinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.myUnLock();
        }, "AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLock.myLock();
            spinLock.myUnLock();
        }, "BB").start();

    }

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come int ");
        while (!atomicReference.compareAndSet(null, thread)) {
            //System.out.println(Thread.currentThread().getName() +  " ****");
        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t unlock ");
        atomicReference.compareAndSet(thread, null);
    }
}
