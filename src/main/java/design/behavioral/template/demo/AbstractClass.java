package design.behavioral.template.demo;

public abstract class AbstractClass {

    public final void templateMethod1() {
        // ...
        method1();
        // ...
        method2();
        // ...
    }

    public final void templateMethod2() {
        // ...
        method3();
        // ...
        method4();
        // ...
    }

    protected abstract void method1();
    protected abstract void method2();
    protected abstract void method3();
    protected abstract void method4();
}
