package api;

import java.util.concurrent.CountDownLatch;

/**
 * @author xzheng
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        closeDoor();
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; ++i) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t  thread");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t end");
    }
}
