package btrace;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HoldNetMain {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new HoldNetTask());
    }
}
