package leetcode.num;

/**
 * x 的平方根
 * https://leetcode-cn.com/problems/sqrtx/
 *
 * @author: dingaimin
 * @date: 2021/2/2 10:10
 */
public class MySqrt {

    public static void main(String[] args) {
        MySqrt test = new MySqrt();
        int res = test.mySqrt(2147395599);
        System.out.println(res);
    }

    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int res = -1;
        while(left <= right) {
            int mid = left + (right-left)/2;
            if((long) mid * mid <= x) {
                res = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return res;
    }
}
