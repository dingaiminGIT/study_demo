package jvm.loadclass;

import java.lang.reflect.Method;

public class MyClassLoaderTest {

    public void test() {
        System.out.println("ddddhhhh");
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("/Users/dingaimin/study/leetcode/study_demo");
        Class<?> clazz = classLoader.loadClass("jvm.loadclass.MyClassLoaderTest");
        Object myClassLoaderTest = clazz.newInstance();
        Method method = clazz.getMethod("test", null);
        method.invoke(myClassLoaderTest, null);

    }
}
