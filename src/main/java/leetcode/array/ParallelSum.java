package leetcode.array;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 美团境外广告 2 面
 * 输入整型数组和K，启动K个线程并行求和
 * input: int[] array , int K
 * output: sum
 * 约束条件： 启动K个线程并行计算
 */
public class ParallelSum {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int K = 3; // 启动3个线程

        int sum = parallelSum(array, K);
        System.out.println("Sum: " + sum);
    }

    public static int parallelSum(int[] array, int K) throws InterruptedException, ExecutionException {
        int length = array.length;
        int chunkSize = (length + K - 1) / K; // 每个线程处理的块大小

        ExecutorService executor = Executors.newFixedThreadPool(K);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, length);
            if (start < end) {
                futures.add(executor.submit(new SumTask(array, start, end)));
            }
        }

        int totalSum = 0;
        for (Future<Integer> future : futures) {
            totalSum += future.get();
        }

        executor.shutdown();
        return totalSum;
    }

    static class SumTask implements Callable<Integer> {
        private final int[] array;
        private final int start;
        private final int end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        }
    }
}
