package designPattern.singleton;

/**
 * @author xzheng
 * 懒汉模式
 * 优点：懒加载启动快，资源占用小，使用时才实例化，无锁。
 * 缺点：非线程安全
 */
public class Singleton01 {
    private static Singleton01 singleton = null;

    private Singleton01() {
        System.out.println(Thread.currentThread().getName() + " \t *********");
    }

    public static Singleton01 getInstance() {
        if (singleton == null) {
            singleton = new Singleton01();
        }
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; ++i) {
            new Thread(() -> {
                Singleton01.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
