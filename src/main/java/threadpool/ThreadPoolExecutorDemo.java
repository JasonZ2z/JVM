package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 如果阻塞队列满了，正在运行的线程数量小于maximumPoolSize，则创建非核心线程立即执行这个任务。
 *
 * @author xzheng
 * @create 2020/12/26
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 0; i < 8; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int tmp = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t  " + tmp);
                    try {
                        TimeUnit.SECONDS.sleep(5L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}