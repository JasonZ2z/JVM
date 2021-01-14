package threadLocal;

/**
 * @author xzheng
 * 用 Synchronized 实现 ThreadLocal 功能
 * Synchronized ： 影响效率
 */
public class ThreadLocalDemo2 {
    private String contest;

    public String getContest() {
        return contest;
    }

    public void setContest(String contest) {
        this.contest = contest;
    }

    public static void main(String[] args) {
        ThreadLocalDemo2 demo = new ThreadLocalDemo2();
        for (int i = 0; i < 5; ++i) {
            new Thread(() -> {
                synchronized (ThreadLocalDemo2.class) {
                    demo.setContest(Thread.currentThread().getName() + " 的data");
                    System.out.println(Thread.currentThread().getName() + "   ---->   " + demo.getContest());
                }
            }, String.valueOf(i)).start();
        }

    }
}