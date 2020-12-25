package lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BlockingQueue 生产者消费者 Demo
 *
 * @author xzheng
 * @create 2020/12/24
 */
public class ProdConsumer_BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        MyData data = new MyData(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t  生产者线程启动");
            try {
                data.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Product").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t  消费者线程启动");
            try {
                data.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        TimeUnit.SECONDS.sleep(5L);
        System.out.println();
        System.out.println("time over, stop!");
        data.stop();
    }
}

class MyData {
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    BlockingQueue<String> blockingQueue;

    public MyData(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void product() throws InterruptedException {
        String data;
        boolean res;
        while (flag) {
            data = atomicInteger.incrementAndGet() + "";
            res = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (res) {
                System.out.println(Thread.currentThread().getName() + "\t  插入队列 " + data + " 成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t  插入队列 " + data + " 失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 停止， flag = false");
    }

    public void consume() throws InterruptedException {
        String data;
        while (flag) {
            data = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == data || "".equals(data)) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒 没有获取物品，消费退出");
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t  消费队列 " + data + " 成功");
        }
    }

    public void stop() {
        this.flag = false;
    }
}

