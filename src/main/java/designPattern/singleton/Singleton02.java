package designPattern.singleton;

/**
 * @author xzheng
 * 懒汉模式 -- 线程安全
 * 优点：启动快，资源占用小，使用时才实例化，但加锁了。
 * 缺点：synchronized 为独占排他锁，并发性能差。即使在创建成功以后，获取实例仍然是串行化操作。
 */
public class Singleton02 {
    private static Singleton02 singleton = null;

    private Singleton02() {
        System.out.println(Thread.currentThread().getName() + " \t *********");
    }

    public static synchronized Singleton02 getInstance() {
        if (singleton == null) {
            singleton = new Singleton02();
        }
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; ++i) {
            new Thread(() -> {
                Singleton02.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
