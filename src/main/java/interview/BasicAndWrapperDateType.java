package interview;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Java 基本数据类型和包装类
 * 包装类的功能丰富（比如计算哈希值、获取类信息）
 * 包装类可以定义泛型类型参数，基本数据类型不可以
 * 包装类实现了序列化
 * 包装类提供类型转换
 * 包装类对高频数据取件做缓存（Integer高频缓存范围 -128 ~ 127，Integer 是唯一可以修改缓存范围的包装类，-XX:AutoBoxCacheMax=1000），Float 和 Double 不会做缓存
 *
 *
 */
public class BasicAndWrapperDateType {

    @Test
    public void valueOfTest() {
        int i = 2;
        Integer integer = Integer.valueOf(i);
        System.out.println(integer);
    }

    /**
     * Integer 缓存范围 -128 ~ 127
     */
    @Test
    public void cacheTest() {
        Integer num1 = 88;
        Integer num2 = 88;
        Integer num3 = 150;
        Integer num4 = 150;

        System.out.println(num1 == num2); // true
        System.out.println(num3 == num4); // false
    }

    /**
     * Integer 和 int 比较，Integer 类型的会自动拆箱为 int 和 int 类型的做比较
     */
    @Test
    public void autoUnboxTest() {
        int num1 = 88;
        Integer num2 = new Integer(88);
        System.out.println(num1 == num2); // true
        System.out.println(num2.equals(num1)); // true

        int num3 = 150;
        Integer num4 = new Integer(150);
        System.out.println(num3 == num4); // true
        System.out.println(num4.equals(num3)); // true
    }

    /**
     * Float 和 Double 不缓存
     *
     */
    @Test
    public void NoCacheTest() {
        Double num1 = 88d;
        Double num2 = 88d;
        Double num3 = 150d;
        Double num4 = 150d;
        System.out.println(num1 == num2); // false
        System.out.println(num3 == num4); // false

    }

    /**
     * 整数以补码的形式存储，最高位符号位 0 表示正值，1 表示 负值
     * Integer.MAX_VALUE + 1 后，最高位变为 1，所以是 负值
     */
    @Test
    public void ComplementCodeTest() {
        int maxValue = Integer.MAX_VALUE;
        System.out.println(maxValue); // 2147483647
        System.out.println(maxValue + 1); // -2147483648
    }

    /**
     * Short 类型 -1 之后转变为 Int 类型，remove 方法在集合找不到 int 类型数据，所以没有删除数据
     */
    @Test
    public void typeConvertTest() {
        Set<Short> set = new HashSet<>();
        for (short i = 0; i < 5; i++) {
            set.add(i);
            set.remove(i - 1);
        }
        System.out.println(set.size()); // 5

        System.out.println("------------------");

        short s = 2;
        // s = s + 1; // 报错，s = s + 1 会导致 short 类型升级为 int 类型
        s += 1; // 不会报错，s += 1 还是 short 类型
        System.out.println(s);

        System.out.println("--------------");

        // float f = 4.8;  // 报错，4.8 是 double 类型，float 类型级别小于 double 类型，报错
        double d = 4.8; // 正常
        float f2 = 4.8f; // 正常
    }

    /**
     * 泛型不可以用基本数据类型
     * 原因是 jvm 编译会类型擦除，如 List<Integer> list 在编译时会转换为 List list ,为什么？
     * 因为泛型是 jdk5 引入的，jvm 为了兼容之前的版本做了泛型擦除，泛型擦除后变成 Object（不能存贮基本数据类型），可以存贮包装类型
     */
    @Test
    public void genericErasureTest() {
        List<Integer> list;
        // List<int> list2; // 报错
    }

    @Test
    public void test() {
        Integer num1 = new Integer(48);
        Integer num2 = new Integer(48);
        Integer num3 = Integer.valueOf(48);
        Integer num4 = Integer.valueOf(48);

        System.out.println(num1 == num2); // false
        System.out.println(num2 == num3); // false
        System.out.println(num3 == num4); // true
    }

}
