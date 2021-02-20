package example.printOddAndEven;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印奇偶数
 * @author xzheng
 * @since 1.0
 */
public class PrintOddAndEven6 {

    public static void main(String[] args) {
        int n = 3;
        Data data = new Data(n);
        for (int i = 0; i < n; ++i) {
            new Thread(() -> {
                while (data.val < 20) {
                    data.print();
                }
            }, String.valueOf(i)).start();
        }
    }

    static class Data {
        private int val = 0;
        private final int cnt;
        private final Lock lock = new ReentrantLock();
        private final List<Condition> conditions = new ArrayList<>();

        public Data(int cnt) {
            this.cnt = cnt;
            for (int i = 0; i < cnt; ++i) {
                conditions.add(lock.newCondition());
            }
        }

        public void print() {
            int index = Integer.parseInt(Thread.currentThread().getName());
            lock.lock();
            try{
                while(val % cnt != index % cnt) {
                    conditions.get(index).await();
                }
                System.out.println(Thread.currentThread().getName() + "-Thread    " + val);
                val++;
                conditions.get(val % cnt).signal();
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}