package designPattern.singleton;

/**
 * @author xzheng
 * 饿汉模式
 * 优点：饿汉模式天生是线程安全的，使用时没有延迟。
 * 缺点：启动时即创建实例，启动慢，有可能造成资源浪费。
 */
public class Singleton04 {
    private volatile static Singleton04 singleton = new Singleton04();

    private Singleton04() {
    }

    public static Singleton04 getInstance() {
        return singleton;
    }
}
