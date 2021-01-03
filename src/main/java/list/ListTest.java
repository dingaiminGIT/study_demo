package list;

import org.junit.jupiter.api.Test;

import java.util.*;

public class ListTest {

    @Test
    public void arraysAsListTest() {
        List<String> friends = Arrays.asList("A", "B", "C");
        friends.add("D");// UnsupportedOperationException
        /*
        java.lang.UnsupportedOperationException
        at java.base/java.util.AbstractList.add(AbstractList.java:153)
        at java.base/java.util.AbstractList.add(AbstractList.java:111)
        at listtest.ListTest.arraysAsListTest(ListTest.java:12)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:688)
        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:149)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:140)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:84)
        at org.junit.jupiter.engine.execution.ExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(ExecutableInvoker.java:115)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.lambda$invoke$0(ExecutableInvoker.java:105)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:104)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:98)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$6(TestMethodTestDescriptor.java:212)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:208)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:137)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:71)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:129)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:127)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:126)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:84)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:129)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:127)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:126)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:84)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:129)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:127)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:126)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:84)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:32)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:51)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:107)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:87)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:53)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:66)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:51)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:87)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:66)
        at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:74)
        at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
        at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
        at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at com.intellij.rt.execution.application.AppMainV2.main(AppMainV2.java:131)
         */
    }

    @Test
    public void test2() {
        List<Dog> list = new ArrayList<>();
        list.add(new Dog(1,"1"));
        list.add(new Dog(2, "2"));

        List<Dog> listCopy = new ArrayList<>();
        for (Dog dog :list) {
            listCopy.add(dog);
        }

        for (Dog dog : listCopy) {
            System.out.println(dog);
        }

        for (Dog dog : list) {
            if (1 == dog.getId()) {
                dog.setName("dddd");
            }
        }

        for (Dog dog : listCopy) {
            System.out.println(dog);
        }
    }

    @Test
    public void listFor() {
        for (int i = 0; i < 4; i++) {
            System.out.println("i:"+i);

            for (int j = 0; j < 3; j++) {
                System.out.println("j:"+j);
                if (j == 1) {
                    break;
                }
                System.out.println("内层for循环结束");
            }
        }

        System.out.println("外层for循环结束");
    }

    @Test
    public void listForeach() {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        a.add("5");

        a.forEach(it->{
            if (it.equals("3")){
                System.out.println("a");
                return;
            } else {
                System.out.println("b");
                return;
            }
        });

        System.out.println("hhhh");
    }

    @Test
    public void testList() {
        List<Data> list = new ArrayList<>();
        list.add(new Data("20181011", "1", "4", "0.12"));
        list.add(new Data("20181011", "2", "4", "0.12"));
        list.add(new Data("20181011", "3", "4", "0.12"));

        System.out.println(list.get(0));
        System.out.println();

    }

    @Test
    public void test1111() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);


    }

    @Test
    public void test333() {
        String a = "1,2,4";
        String[] split = a.split(",");

    }


    @Test
    public void testListRemoveAll() {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        a.add("5");
        List<String> b = Arrays.asList("4", "5", "6");
        a.removeAll(b);
        System.out.println(a);
    }

    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("url");
        list.add("spm");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    @Test
    public void test2d() {
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        for (int i = 0; i < list.size(); i++) {
            //list.add(i,"d");
            list.add("e");
            System.out.println("size:" + list.size());
            System.out.println(list);
        }
    }

    @Test
    public void modifyTest() {

        List<Person> list = new ArrayList<>();
        list.add(new Person(1, "张1"));
        list.add(new Person(2, "张2"));
        list.add(new Person(3, "张3"));
        list.add(new Person(4, "张4"));

        for (int i = 0; i < list.size(); i++) {
            Person person = list.get(i);
            if (person.getAge() == 2) {
                list.remove(person);
            }
            //list.add(new Person(2, "张2copy"));
            System.out.println("size:" + list.size());
        }

        // 遍历list的同时，可以set值，但不能remove
        /*for (Person p : list) {
            if (p.getAge() == 2) {
                //p.setName("ddddd");
                //list.remove(p);
                list.add(new Person(2, "张2copy"));
            }
        }*/

        for (Person p : list) {
            System.out.println(p);
        }
    }

    /**
     * 求差集
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public String[] diff(String[] arr1, String[] arr2) {
        List<String> a = Arrays.asList(arr1);
        List<String> b = Arrays.asList(arr2);
        a.retainAll(b);
        for (String i : a) {
            System.out.println(i);
        }

        return (String[]) a.toArray();
    }

    @Test
    public void tttt() {
        String[] arra = new String[3];
        arra[0]="你好";
        arra[1]="测试";
        String[] arras = new String[2];
        arras[0]="测试";

        ListTest listTest = new ListTest();
        String[] diff = listTest.diff(arra, arras);

    }

    @Test
    public void eee() {
        String[] arra = new String[3];
        arra[0]="你好";
        arra[1]="测试";
        String[] arras = new String[2];
        arras[0]="测试";
        List<String> arraList = new ArrayList<>(Arrays.asList(arra));
        System.out.println(arraList.size());
        List<String> arrasList = new ArrayList<>(Arrays.asList(arras));
        System.out.println(arrasList.size());
        arraList.retainAll(arrasList);
        for(String str:arraList) {
            System.out.println(str);
        }
    }

    @Test
    public void retainAllTest() {

        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("");
        a.add("2");
        a.add("3");
        a.add("4");
        a.add("5");
        a.add(null);

        List<String> b = new ArrayList<>();
        b.add("5");
        b.add(null);

        System.out.println(a.size() + "  " + b.size());
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        a.retainAll(b);
        for (String i : a) {
            System.out.println(i);
        }
    }

    @Test
    public void listForTest() {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("");
        a.add("2");
        a.add("3");
        a.add("4");
        a.add("5");

        a.forEach(b->{
            if (b.equals("2")){
                System.out.println("2");
                return;
            }
        });

        List<String> list = new ArrayList<>();
        System.out.println(list.isEmpty());

        // list不能为null
        //list = null;
        list.forEach(info -> {
            System.out.println(info);
        });

        System.out.println("ddd");
    }

    @Test
    public void listTest() {
        long start = System.currentTimeMillis();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }
        long end = System.currentTimeMillis();
        long time = end- start;
        System.out.println(time);
    }

    @Test
    public void LinkedHashSetTest() {
        long start = System.currentTimeMillis();
        LinkedHashSet<String> set = new LinkedHashSet<>();
        /*set.add("1");
        set.add("2");
        set.add("2");*/
        for (int i = 0; i < 40; i++) {
            set.add(i+"");
        }
        long end = System.currentTimeMillis();
        long time = end- start;
        System.out.println(time);
    }

    @Test
    public void containsTest() {
        List<String> list = new ArrayList<>();
        list.add("1");
        System.out.println(list.contains(null));
    }

    public static void main(String[] args) {
        System.out.println("aaaa");
    }

}
