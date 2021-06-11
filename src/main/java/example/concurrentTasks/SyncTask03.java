package example.concurrentTasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncTask03 {
    static AtomicInteger count = new AtomicInteger(10);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Integer>> resultList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; ++i) {
            CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> {
                int sec = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.MILLISECONDS.sleep(sec);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task is completed! cost " + sec + "s, left " + count.decrementAndGet());
                return 1;
            }, threadPool);
            resultList.add(task);
        }
        int res = 0;
        for (CompletableFuture<Integer> future : resultList) {
            res += future.get();
        }
        System.out.println(res);
        System.out.println("all task is completed! cost: " + (System.currentTimeMillis() - start) + "ms");
    }
}