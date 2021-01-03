package thread;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {

    private String name;

    public Task(String name){
        this.name = name;
    }

    private static int c = 1;

    @Override
    public String call() throws Exception {
        System.out.println(this.name+" 开始任务:"+System.currentTimeMillis());
        Thread.sleep(2);
        System.out.println(this.name+" 任务结束:"+System.currentTimeMillis());
        return this.name;
    }
}
