package javabase;

public interface BInterface {

    default void eat() {
        System.out.println("B eat");
    };
}
