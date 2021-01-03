package async;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureDemo {

    private final static int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(PROCESSORS,
            PROCESSORS * 2,
            1, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void runAsync() throws ExecutionException, InterruptedException {
        // 创建无返回值异步任务
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务计算结束");
        });

        // 获取异步任务结果
        System.out.println(future.get());
    }

    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        // 创建有返回值的异步任务
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("计算任务结束");
            return "supplyAsync";
        });

        // 获取异步任务结果
        System.out.println(future.get());
    }

    @Test
    public void thenRun() throws ExecutionException, InterruptedException {
        // 创建有返回值的异步任务1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结束");
            return "1";
        });

        // 在 future1 上添加事件，future1完成后回调该事件，并返回新的future
        CompletableFuture<Void> future2 = future1.thenRun(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结束");
            System.out.println(Thread.currentThread().getName());
        });

        System.out.println(future2.get());
    }

    @Test
    public void thenAccept() throws ExecutionException, InterruptedException {
        // 创建有返回值的异步任务1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结束");
            System.out.println(Thread.currentThread().getName());
            return "1";
        });

        // 在 future1 上添加事件，future1完成后回调该事件，并返回新的future
        CompletableFuture<Void> future2 = future1.thenAccept((t) -> {
            // 获取future1的结果，并做处理
            t = t + "2";
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("在任务1的基础上做处理，t=" + t );
            System.out.println("任务2结束");
            System.out.println(Thread.currentThread().getName());
        });

        System.out.println(future2.get());
    }

    @Test
    public void thenAcceptAsync() throws ExecutionException, InterruptedException {
        // 创建有返回值的异步任务1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结束");
            System.out.println(Thread.currentThread().getName());
            return "1";
        });

        // 在 future1 上添加事件，future1完成后回调该事件，并返回新的future
        CompletableFuture<Void> future2 = future1.thenAcceptAsync((t) -> {
            // 获取future1的结果，并做处理
            t = t + "2";
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("在任务1的基础上做处理，t=" + t );
            System.out.println("任务2结束");
            System.out.println(Thread.currentThread().getName());
        }, POOL_EXECUTOR);

        System.out.println(future2.get());
    }

    @Test
    public void thenApply() throws ExecutionException, InterruptedException {
        // 创建有返回值的异步任务1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结束");
            return "1";
        });

        CompletableFuture<String> future2 = future1.thenApply((t) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结束");
            // 在任务1的基础上做处理
            return t + "2";
        });

        System.out.println(future2.get());
    }

    @Test
    public void whenComplete() throws InterruptedException {
        // 创建有返回值的异步任务1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结束");
            return "1";
        });

        // 给future1添加回调
        future1.whenComplete((t, u) -> {
            // 如果任务1没有异常，输出任务1的结果
            if (u == null) {
                System.out.println(t);
            } else {
                // 打印异常信息
                System.out.println(u.getCause());
            }
        });

        // 挂起当前线程等待异步任务结束
        Thread.currentThread().join();
    }

    /**
     * 实现当一个CompletableFuture执行结束后，再执行另一个ComplatableFuture
     *
     */
    @Test
    public void thenCompose() throws ExecutionException, InterruptedException {
        OrderInfo orderInfo = new OrderInfo("1", "饺子", 22d);
        CompletableFuture<String> future = order(orderInfo).thenCompose(price -> pay(price));
        System.out.println(future.get());

    }

    @Test
    public void thenCombine() throws ExecutionException, InterruptedException {
        OrderInfo orderInfo = new OrderInfo("1", "饺子", 22d);
        CompletableFuture<String> future = order(orderInfo).thenCombine(pay(22d), (order, price) -> order + price);
        System.out.println(future.get());
    }

    @Test
    public void allOf() throws ExecutionException, InterruptedException {
        List<CompletableFuture> futureList = new ArrayList<>();
        futureList.add(order(new OrderInfo("1", "饺子", 22d)));
        futureList.add(order(new OrderInfo("2", "口罩", 22d)));
        futureList.add(order(new OrderInfo("3", "消毒液", 22d)));
        futureList.add(pay(22d));
        futureList.add(pay(23d));
        futureList.add(pay(24d));

        CompletableFuture<Void> future = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        System.out.println(future.get());
    }

    @Test
    public void anyOf() throws ExecutionException, InterruptedException {
        List<CompletableFuture> futureList = new ArrayList<>();
        futureList.add(order(new OrderInfo("1", "饺子", 22d)));
        futureList.add(order(new OrderInfo("2", "口罩", 22d)));
        futureList.add(order(new OrderInfo("3", "消毒液", 22d)));

        CompletableFuture<Object> future = CompletableFuture.anyOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        System.out.println(future.get());
    }

    public static CompletableFuture<Double> order(OrderInfo orderInfo) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("下单完成，订单信息为 " + orderInfo.toString());
            return orderInfo.getPrice();
        });
    }

    public static CompletableFuture<String> pay(Double price) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "支付完成，金额为" + price;
        });
    }
}

class OrderInfo {
    String orderId;
    String orderName;
    double price;

    public OrderInfo(String orderId, String orderName, double price) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId='" + orderId + '\'' +
                ", orderName='" + orderName + '\'' +
                ", price=" + price +
                '}';
    }
}
