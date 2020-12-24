package collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xzheng
 * @create 2020/12/24
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        System.out.println(queue.add("a"));
        System.out.println(queue.element());
        System.out.println(queue.remove("a"));

        System.out.println(queue.offer("a"));
        System.out.println(queue.peek());
        System.out.println(queue.poll());

        System.out.println("================");

        queue.put("x");
        queue.put("x");
        queue.put("x");
        System.out.println("================");
        //queue.put("x");

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue);

        queue.offer("a", 2L, TimeUnit.SECONDS);
        queue.offer("a", 2L, TimeUnit.SECONDS);
        queue.offer("a", 2L, TimeUnit.SECONDS);
        System.out.println("===============1");
        queue.offer("a", 2L, TimeUnit.SECONDS);
        System.out.println("===============2");
        System.out.println(queue.poll(2L, TimeUnit.SECONDS));
        System.out.println(queue.poll(2L, TimeUnit.SECONDS));
        System.out.println(queue.poll(2L, TimeUnit.SECONDS));
        System.out.println("===============3");
        System.out.println(queue.poll(2L, TimeUnit.SECONDS));
        System.out.println("===============4");
    }
}
