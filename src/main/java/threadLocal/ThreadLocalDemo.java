package threadLocal;

/**
 * @author xzheng
 */
public class ThreadLocalDemo {
    private String contest;
    ThreadLocal<String> t1 = new ThreadLocal<>();

    public String getContest() {
        //return contest;
        return t1.get();
    }

    public void setContest(String contest) {
        //this.contest = contest;
        t1.set(contest);
    }

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        for (int i = 0; i < 5; ++i) {
            new Thread(() -> {
                demo.setContest(Thread.currentThread().getName() + " 的data");
                System.out.println(Thread.currentThread().getName() + "   ---->   " + demo.getContest());
            }, String.valueOf(i)).start();
        }

    }
}