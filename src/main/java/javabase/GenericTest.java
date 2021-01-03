package javabase;

public class GenericTest {

    public static void main(String[] args) {
        //Plate<Fruit> p = new Plate<Apple>(new Apple());
        /*Plate<? extends Fruit> p = new Plate<Apple>(new Apple());
        p.set(new Fruit());
        p.set(new Apple());
        Fruit fruit = p.get();
        Object object = p.get();
        Apple apple = p.get();*/
        /*Plate<? super Fruit> p2 = new Plate<Fruit>(new Fruit());
        p2.set(new Apple());
        p2.set(new Fruit());
        Apple apple = p2.get();
        Fruit fruit = p2.get();
        Object object = p2.get();*/
    }
}

/**
 * 盘子
 * @param <T>
 */
class Plate<T> {
    private T item;
    public Plate(T t) {
        this.item = t;
    }

    public void set(T t) {
        this.item = t;
    }

    public T get() {
        return this.item;
    }
}

/**
 * 水果
 */
class Fruit{}

/**
 * 苹果
 */
class Apple extends Fruit{

}
