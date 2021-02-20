package example.printOddAndEven;

/**
 * 两个线程交替打印奇偶数
 * @author xzheng
 * @since 1.0
 */
public class PrintOddAndEven2 {
    private static int val = 0;
    private static final Object object = new Object();


    public static void main(String[] args) {

        new Thread(() -> {
            while (val < 10) {
                synchronized (object){
                    try {
                        System.out.println(Thread.currentThread().getName() + " : " + val++);
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Even").start();

        new Thread(() -> {
            while (val < 10) {
                synchronized (object){
                    try {
                        System.out.println(Thread.currentThread().getName() + " : " + val++);
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Odd").start();
    }
}