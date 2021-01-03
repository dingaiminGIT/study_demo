package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

    public static void main(String[] args) {
        ThreadPools.init();

        List<Future<String>> results = new ArrayList<>();
        for(int i = 1; i<= 20; i++){
            try {
                results.add(ThreadPools.submit(new Task("任务:"+i)));
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        for(Future<String> f :results){
            try{
                String result = f.get(3, TimeUnit.MILLISECONDS);
                System.out.println("任务执行的结果="+result);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        System.exit(0);
    }
}
