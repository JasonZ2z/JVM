package example.printOddAndEven;

/**
 * 两个线程交替打印奇偶数
 * @author xzheng
 * @since 1.0
 */
public class PrintOddAndEven1 {
    private static int val = 0;
    private static final Object obj = new Object();
    public static void main(String[] args) {

        new Thread(() -> {
            while (val < 10) {
                synchronized (obj){
                    if((val & 1) == 0) System.out.println(Thread.currentThread().getName() + " : " + val++);
                }
            }
        }, "Even").start();

        new Thread(() -> {
            while (val < 10) {
                synchronized (obj){
                    if((val & 1) == 1) System.out.println(Thread.currentThread().getName() + " : " + val++);
                }
            }
        }, "Odd").start();
    }
}