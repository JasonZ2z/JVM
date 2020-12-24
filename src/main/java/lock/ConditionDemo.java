package lock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xzheng
 * @create 2020/12/24
 */
public class ConditionDemo {
    public static void main(String[] args) {
        Item item = new Item();
        for (int i = 0; i < 4; ++i) {
            new Thread(() -> {
                item.print();
            }, String.valueOf(i % 3)).start();
        }
    }
}

class Item {
    private int number = 0;// A : 1 B : 2 C :3

    private final Lock lock = new ReentrantLock();
    private final List<Condition> list = Arrays.asList(lock.newCondition(), lock.newCondition(), lock.newCondition());

    public void print() {
        int index = Integer.parseInt(Thread.currentThread().getName());
        lock.lock();
        try {
            while (index != number) {
                list.get(index).await();
            }
            for (int i = 0; i < 5; ++i) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = (number + 1) % 3;
            list.get(number).signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
