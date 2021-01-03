package javabase;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ArrayTest {

    @Test
    public void binarySearch() {
        int[] arr = {4,3,5,6,1,8};
        // 利用二分查找，前提是保证数组有序
        Arrays.sort(arr);
        int i = Arrays.binarySearch(arr, 1);
        System.out.println(i);
    }

    /**
     * 数组初始化的方式
     */
    @Test
    public void test3() {
        // 方式一
        //int[] arr = new int[]{1,2,3,4,5};

        // 方式二
        //int[] arr = new int[5];
        //arr[0] = 1;

        // 方式三
        //int[] arr = {1,2,3,4,5};

        // 编译不通过
        //int[] arr = new int[5]{1,2,3,4,5};
    }

    @Test
    public void test1() {
        int[] arr = {10, 20, 30, 40, 50};
        int[] ints = Arrays.copyOfRange(arr, 0, 2);      // returns {10, 20}
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
        Arrays.copyOfRange(arr, 1, 4);          // returns {20, 30, 40}
        Arrays.copyOfRange(arr, 2, arr.length); // returns {30, 40, 50} (length = 5)
    }

    @Test
    public void test2() {
        int[] a = {2,2,2,2,2,0,0,0,0};

        int[] a1 = {0,0,0,0,0,1,1,1,1};
        int[] b=new int[9];
        //参数：数组源，拷贝的起始下标，目标数组，填写的起始下标，拷贝的长度）
        System.arraycopy(a, 0, b, 0, 5);
        System.arraycopy(a1, 5, b, 5, 4);
        for(int i=0;i<b.length;i++){
            System.out.print(b[i]);
        }
    }

    @Test
    public void ArrayIndexOutOfBundsExceptionTest() {
        String[] a = new String[]{"q","b"};
        System.out.println(a[3]);
    }

    @Test
    public void testArray() {
        int[] a = new int[1000];
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }
}
