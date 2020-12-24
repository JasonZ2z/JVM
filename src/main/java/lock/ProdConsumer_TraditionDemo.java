package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xzheng
 * @create 2020/12/24
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                try {
                    data.add();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                try {
                    data.minus();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}

class Data {
    private int num = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void add() throws Exception {
        lock.lock();
        try {
            //判断
            while (num != 0) {
                condition.await(); //等待
            }
            // do sth
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //通知 + 唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void minus() throws Exception {
        lock.lock();
        try {
            //判断
            while (num == 0) {
                condition.await(); //等待
            }
            // do sth
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //通知 + 唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
