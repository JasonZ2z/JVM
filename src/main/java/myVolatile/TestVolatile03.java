package myVolatile;

/**
 * @author xzheng
 */
public class TestVolatile03 {

    int a = 0;
    volatile boolean flag = false;

    public void m1() {
        a = 1;
        flag = true;
    }

    public void m2() {
        if (flag) {
            a = a + 5;
            System.out.println("retValue : " + a);
        }
    }

    /**
     * 保证特定操作的执行顺序
     * 保证某些变量的内存可见性（也是volatile可见性的实现原因）-> 强制刷出各种CPU的缓存数据，因此CPU上的线程都能读取到数据的最新版本。
     * 在指令之间插入一条Memory Barrier，告诉编译器和CPU，禁止内存屏障前后的指令执行重排优化。
     */
    public static void main(String[] args) {

    }
}