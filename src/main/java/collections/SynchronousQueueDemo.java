package collections;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xzheng
 * @create 2020/12/24
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + "\t  put 1");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t  put 2");
                queue.put("3");
                System.out.println(Thread.currentThread().getName() + "\t  put 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "\t  take " + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t  take " + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "\t  take " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

    }
}
