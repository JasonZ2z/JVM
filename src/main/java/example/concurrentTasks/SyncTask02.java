package example.concurrentTasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncTask02 {
    static AtomicInteger count = new AtomicInteger(10);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        List<Future<Integer>> resultList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; ++i) {
            Callable<Integer> task =  () -> {
                int sec = ThreadLocalRandom.current().nextInt(10);
                TimeUnit.MILLISECONDS.sleep(sec);
                System.out.println("task is completed! cost " + sec + "s, left " + count.decrementAndGet());
                return 1;
            };
            Future<Integer> result = threadPool.submit(task);
            resultList.add(result);
        }
        TimeUnit.SECONDS.sleep(3);
        int res = 0;
        for (Future<Integer> future : resultList) {
            res += future.get();
        }
        System.out.println(res);
        System.out.println("all task is completed! cost: " + (System.currentTimeMillis() - start) + "ms");
    }
}