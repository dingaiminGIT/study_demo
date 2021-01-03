package jvm.loadclass;

public class Child extends Parent {
    static {
        System.out.println("Child init");
        boolean a = false;
    }
}
