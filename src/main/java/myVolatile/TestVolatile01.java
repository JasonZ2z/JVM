package myVolatile;

/**
 * @author xzheng
 */
public class TestVolatile01 {

    /**
     * 可见性证明
     * 不加 volatile 关键字， main 线程不会收到通知，没有输出
     * 加了 volatile 关键字，A线程结束之后，通知 main 线程，输出正确。
     */
    public static void main(String[] args) {
        MyData01 myData = new MyData01();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                myData.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "  finish");
        }, "A").start();

        while (myData.i == 0) {

        }
        System.out.println("end");
    }
}

class MyData01 {
    volatile int i = 0;

    public void add() {
        i += 10;
    }
}