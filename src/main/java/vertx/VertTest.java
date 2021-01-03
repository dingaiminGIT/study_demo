package vertx;

import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class VertTest {

    @Test
    public void test01() throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        WebClient webClient = WebClient.create(vertx);
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            vertx.runOnContext(v -> {
                String thread = Thread.currentThread().getName();
                webClient.getAbs("http://www.baidu.com").send(ar -> {
                    System.out.println(index + " " + thread + " ->" + Thread.currentThread().getName());
                    latch.countDown();
                });
            });
        }
        latch.await();
    }

    @Test
    public void test02() throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        WebClient webClient = WebClient.create(vertx);
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            vertx.runOnContext(v -> {
                String thread = Thread.currentThread().getName();
                Context context = vertx.getOrCreateContext();
                webClient.getAbs("http://www.baidu.com").send(ar -> {
                    // 手动切回去
                    context.runOnContext(v1->{
                        System.out.println(index + " " + thread + " ->" + Thread.currentThread().getName());
                        latch.countDown();
                    });
                });
            });
        }
        latch.await();
    }
}
