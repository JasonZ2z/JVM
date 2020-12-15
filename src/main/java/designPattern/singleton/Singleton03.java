package designPattern.singleton;

/**
 * @author xzheng
 * 懒汉模式 -- 双重加锁检查 DCL
 * 优点：懒加载，线程安全。
 * 注：实例必须有 volatile 关键字修饰，其保证初始化完全。
 */
public class Singleton03 {
    /**
     * DCL 不一定线程安全，原因是因为有指令重排机制的存在，加入volatile可以防止指令重排。
     * 可能出现的现象： 对象没有完成初始化工作。
     */
    private volatile static Singleton03 singleton = null;

    private Singleton03() {
        System.out.println(Thread.currentThread().getName() + " \t *********");
    }

    public static Singleton03 getInstance() {
        if (singleton == null) {
            synchronized (Singleton03.class) {
                if (singleton == null) {
                    singleton = new Singleton03();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; ++i) {
            new Thread(() -> {
                Singleton03.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
