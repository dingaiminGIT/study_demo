package design.behavioral.template.demo;

public class SubA extends Base {

    // 只需要重写 method1
    @Override
    public void method1() {
        System.out.println("重写 method1");
    }

    public static void main(String[] args) {
        Base A = new SubA();
        A.templateMethod1();
    }
}
