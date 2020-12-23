package api;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xzheng
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier end = new CyclicBarrier(7, () -> {
            System.out.println("end");
        });
        for (int i = 0; i < 7; ++i) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t index");
                try {
                    end.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
