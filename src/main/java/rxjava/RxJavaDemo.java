package rxjava;

import common.Person;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class RxJavaDemo {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("张三", 28));
        list.add(new Person("李四", 10));
        list.add(new Person("王五", 8));

        // Person 列表转为 Flowable 流对象
        Flowable.fromArray(list.toArray(new Person[0]))
                // 过滤年龄大于8岁的
                .filter(person -> person.getAge() > 8)
                // 将Person转换为name
                .map(person -> person.getName())
                // 订阅输出，只有执行到subscribe时数据流才会执行
                .subscribe(System.out::println);
    }

    @Test
    public void sync() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        long start = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        Flowable.fromArray(list.toArray(new String[0]))
                .map(num -> print(num))
                .subscribe(System.out::println);

        long end = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        System.out.println("耗时：" + (end - start));
    }

    @Test
    public void observeon() throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        Flowable.fromArray(list.toArray(new String[0]))
                // 线程从main切换到IO线程执行
                .observeOn(Schedulers.io())
                .map(num -> print(num))
                .subscribe(System.out::println);

        System.out.println("main线程等待;io线程执行任务");
        // 挂起main线程，因为IO线程是deamon线程，main线程执行完毕，就没有用户线程了，所以deamon线程也不执行
        Thread.currentThread().join();
    }

    @Test
    public void subscribeOn() {
        long start = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        Flowable.fromCallable(() -> {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
            return "Done";
            // 切换发射元素的线程
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
        .subscribe(System.out::println);

        long end = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        System.out.println("耗时：" + (end - start));
        try {
            // 等待流结束
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void flapMap() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }

        long start = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        Flowable.fromArray(list.toArray(new String[0]))
                .flatMap(num -> Flowable.just(num) // 通过Flowable.just将num作为数据源生成Flowable对象
                                            // 元素发送使用IO线程
                                            .subscribeOn(Schedulers.io())
                                            .map(r -> print(r)))
                // 这里阻塞main线程
                .blockingSubscribe(System.out::println);

        long end = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        System.out.println("耗时：" + (end - start));

    }

    public static String print(String num) {
        String result = Thread.currentThread().getName() + "," + num;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
