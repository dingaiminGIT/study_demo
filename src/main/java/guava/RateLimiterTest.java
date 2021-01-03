package guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class RateLimiterTest {

    public static void main(String[] args) {
        // 1 秒生成10个令牌， 0.1s 生成一个
        RateLimiter rateLimiter = RateLimiter.create(10);
        // 可预热的限流器
        // RateLimiter rateLimiter1 = RateLimiter.create(10, 3, TimeUnit.SECONDS);
        while (true) {
            System.out.println("get 1 tokens: " + rateLimiter.acquire(20) + "s");
        }

    }
}
