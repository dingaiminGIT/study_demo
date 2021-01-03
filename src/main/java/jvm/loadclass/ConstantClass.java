package jvm.loadclass;

public class ConstantClass {
    public static final String CONSTANT = "constant";
    static {
        System.out.println("ConstantClass init");
    }
}
