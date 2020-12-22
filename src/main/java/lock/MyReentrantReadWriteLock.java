package lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xzheng
 */
public class MyReentrantReadWriteLock {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; ++i) {
            final int tmp = i;
            new Thread(() -> {
                myCache.put(tmp, tmp);
            }, String.valueOf(i)).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; ++i) {
            final int tmp = i;
            new Thread(() -> {
                myCache.get(tmp);
            }, String.valueOf(i)).start();
        }
    }


}

class MyCache {
    volatile Map<Integer, Object> map = new HashMap<>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(Integer key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t writing start \t" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t writing  done \t" + key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(Integer key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t reading start \t" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t reading  done \t" + o);
        } finally {
            lock.readLock().unlock();
        }
    }
}