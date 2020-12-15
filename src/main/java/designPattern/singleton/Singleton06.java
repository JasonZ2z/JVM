package designPattern.singleton;

/**
 * @author xzheng
 * 静态内部类实现 -- Holder模式
 * 优点：将懒加载和线程安全完美结合的一种方式（无锁）。（推荐）
 */
public class Singleton06 {
    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder {
        //静态初始化器，由JVM来保证线程安全
        private static Singleton06 instance = new Singleton06();
    }

    private Singleton06() {
    }

    public static Singleton06 getInstance() {
        return SingletonHolder.instance;
    }
}
