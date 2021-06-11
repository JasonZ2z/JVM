package example.concurrentTasks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SyncTask01 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; ++i) {
            new Thread(() -> {
                int sec = ThreadLocalRandom.current().nextInt(5);
                try {
                    TimeUnit.MILLISECONDS.sleep(sec);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(SyncTask01.class){
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName() + " task is Completed! cost time " + sec + "s, left "  + latch.getCount());
                }
            }, String.valueOf(i)).start();
        }
        latch.await();
        System.out.println("all task is completed! cost: " + (System.currentTimeMillis() - start) + "ms");
    }
}