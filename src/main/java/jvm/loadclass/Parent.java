package jvm.loadclass;

public class Parent {
    public static int v = 60;
    static {
        System.out.println("Parent init");
    }
}
