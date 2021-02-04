package leetcode;

import java.util.Random;

/**
 * 参数int[]  2 3 5 10
 * 返回值 参数之一(2或3或5或10)，但是每个数几率不同。下面是返回每个数几率的计算方式。
 * 2  -> 2/(2+3+5+10)  返回2的几率是10%
 * 3 -> 3/(2+3+5+10) 返回3的几率是15%
 * 5-> 5/(2+3+5+10)
 * 10 -> 10/(2+3+5+10)
 *
 * @author: dingaimin
 * @date: 2021/1/28 21:31
 */
public class ABTest {

    public static void main(String[] args) {
        ABTest abTest = new ABTest();
        int[] num = new int[]{1,2,3,4};

        int a = 0,b = 0,c = 0,d = 0;
        for (int i = 0; i < 1000; i++) {
            int ab = abTest.ab(num);
            if (ab == 1) {
                a++;
            } else if (ab == 2) {
                b++;
            } else if (ab == 3) {
                c++;
            } else if (ab == 4){
                d++;
            }
        }
        int sum = 1000;
        System.out.println("1:" + a * 1.0 /sum + ", 2 : " + b*1.0 / sum + ", 3 : " + c *1.0/ sum + ", 4:" + d *1.0/ sum);
    }

    public int ab(int[] num) {

        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
        }
        Random random = new Random();
        int n = random.nextInt(sum);

        int sum2 = 0;
        for (int j = 0; j < num.length; j++) {
            sum2 += num[j];
            if (n < sum2) {
                return num[j];
            }
        }
        return 0;
    }
}
