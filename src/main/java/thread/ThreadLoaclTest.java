package thread;

import org.junit.jupiter.api.Test;

public class ThreadLoaclTest {

    @Test
    public void test1() {
        ThreadLocal<Object> local = new ThreadLocal<>();
        local.set("a");
        local.set("b");

        Object o = local.get();
        System.out.println(o);

    }


}
