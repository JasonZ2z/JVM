package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Callable + FutureTask
 *
 * @author xzheng
 * @create 2020/12/25
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task1 = new FutureTask<>(new MyThread());
        FutureTask<Integer> task2 = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(task1, "AA");

        Thread t2 = new Thread(task2, "BB");
        t1.start();
        t2.start();
        int a = 10;

        while (!task1.isDone()) {
            // run
        }
        System.out.println(a + task1.get());
        System.out.println(a + task2.get());
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "********* start ");
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}