package javabase;

import list.Person;
import org.junit.jupiter.api.Test;

public class StringBufferTest {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        System.out.println(sb.length());
        sb.append("aaa");
        System.out.println(sb.length());
        if (sb != null && sb.length() > 0) {
            sb.append("bbb");
        }
        System.out.println(sb.length());
    }

    @Test
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.length());
        sb.append("aaa");
        System.out.println(sb.length());
        if (sb != null && sb.length() > 0) {
            sb.append("bbb");
        }
        System.out.println(sb.length());
    }

    @Test
    public void appendTest() {
        Person person = new Person(26, "白牙");
        StringBuffer sb = new StringBuffer();
        sb.append("between '").append(person.getName()).append("' and '").append(person.getAge()).append("'\n");
        System.out.println(sb.toString());
    }

}
