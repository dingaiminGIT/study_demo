package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class RetryUtil {

    private static Map<String, Integer> map = new HashMap<>();


    public static void main(String[] args) throws Exception {
        /*List<String> result = retry(2, () -> {
            List<String> list = null;
            list = foo();
            return list;
        },(list, e) -> {
            if (e != null || list == null || list.isEmpty()) {
                return true;
            }
            return false;
        });
        result.forEach((item)-> System.out.println(item));*/

        map.put("a",1);
        List<String> result1 = retry(3,
                ()-> foo(map.get("a")),
                (list, e) -> e != null || list == null || list.isEmpty());



        /*List<String> result = null;
        int retryCount = 0;
        boolean callSwitch = true;
        while (callSwitch && retryCount <= 2) {
            try {
                result = foo();
                callSwitch = false;
            } catch (Exception e) {
                callSwitch = true;
                retryCount++;
            }
        }*/
        // 后面对 result 进行处理
    }

    private static List<String> foo(Integer a) throws Exception{
        if (a == 1) {
            map.put("a", ++a);
            System.out.println(1/0);
        }
        map.put("a", ++a);
        List<String> list = new ArrayList<>();
        list.add("1");
        return list;
    }

    public static <T> T retry(int maxRetryCount, SupplierWithException<T> supplier, BiFunction<T, Exception, Boolean> consumer) throws Exception{
        T result = null;
        Exception exception = null;

        int retryCount = 0;
        boolean callMethod = true;
        while (callMethod && retryCount <= maxRetryCount) {
            exception = null;
            try {
                result = supplier.get();
            } catch (Exception e) {
                // 达到最大重试次数后还调用失败，就把异常抛出给业务端处理
                if (retryCount >= maxRetryCount) {
                    throw e;
                }
                exception = e;
            }
            // 对结果进行判断
            callMethod = consumer.apply(result, exception);
            if (callMethod) {
                retryCount++;
                if (retryCount <= maxRetryCount) {
                    System.out.println("重试次数" + retryCount);
                }
            }
        }
        return result;
    }
}

@FunctionalInterface
interface SupplierWithException<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws Exception;
}

