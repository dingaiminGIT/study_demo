package leetcode;

/**
 * 实现扑克牌洗牌操作-即打乱数组
 *
 * @author: dingaimin
 * @date: 2021/2/7 11:55
 */
public class RandomArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int lengh = nums.length;
        for (int i = 0; i < lengh; i++) {
            int index = (int) (Math.random()*lengh);
            int temp = nums[index];
            nums[index] = nums[i];
            nums[i] = temp;
        }

        for (int i = 0; i < lengh; i++) {
            System.out.println(nums[i]);
        }

        /*for (int i = 0; i < 100; i++) {
            System.out.println((int)(Math.random() * 10));
        }*/
    }
}
