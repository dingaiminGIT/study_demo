package javabase;

import java.util.ArrayList;
import java.util.List;

public class Dog {

    private int age;
    private Integer head;
    private String name;

    public Dog(int age, Integer head, String name) {
        this.age = age;
        this.head = head;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", head=" + head +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<Dog> list = new ArrayList<>();
        list.add(new Dog(1,1,"dd"));
        list.add(new Dog(2,2,"cc"));

        System.out.println(list.toString());
        //list.stream().forEach(dog -> dog.toString());
    }
}
