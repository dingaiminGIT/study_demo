package javabase;

public interface AInterface {

    default void eat() {
        System.out.println("A eat");
    };

}
