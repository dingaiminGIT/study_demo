package javabase;

public interface CInterface extends AInterface, BInterface {

    @Override
    default void eat() {

    }
}
