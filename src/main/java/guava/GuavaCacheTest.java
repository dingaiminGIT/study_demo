package guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class GuavaCacheTest {

    public static void main(String[] args) {
        Cache<String, Long> build = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.HOURS).maximumSize(1000000).recordStats().build();
        build.put("111.111.111.111", 11L);

        build.put("111.111.111", 11L);

    }
}
