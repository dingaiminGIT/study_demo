package javabase;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Sort {

    /**
     * 冒泡排序:依次比较两个相邻的数，把大的放后面
     */
    @Test
    public void pop() {
        int[] arr = {4,3,5,6,1,8};
        System.out.println("排序前：" + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length -i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    // 交换元素
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    /**
     * 选择排序：从待排序的数据元素中选最小的或最大的，放到已经排好序的后面
     */
    @Test
    public void select() {
        int[] arr = {4,3,5,6,1,8};
        System.out.println("排序前：" + Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            int lowIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                // 找最小的元素对应的下标
                if (arr[j] < arr[lowIndex]) {
                    lowIndex = j;
                }
            }

            // 交换数据
            int temp = arr[i];
            arr[i] = arr[lowIndex];
            arr[lowIndex] = temp;
        }
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
