package thread;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void test() {
        ThreadLocal threadLocal = new InheritableThreadLocal();
        threadLocal.set("白牙");

        ThreadLocal threadLocal1 = new ThreadLocal();
        threadLocal1.set("白牙");
        System.out.println(threadLocal.get());
        System.out.println(threadLocal1.get());

        new Thread(() -> {
            System.out.println(threadLocal.get());
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal.get().equals(threadLocal1.get()));
            threadLocal.remove();
            threadLocal1.remove();
        }).start();
    }

}
