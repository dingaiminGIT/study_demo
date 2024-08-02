package leetcode.string;

/**
 * 验证回文串
 * https://leetcode-cn.com/problems/valid-palindrome/
 *
 * @author: dingaimin
 * @date: 2021/2/2 21:15
 */
public class IsPalindrome {

    public boolean isPalindrome(String s) {
        // 先对字符串进行处理
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        // 双指针
        int left = 0, right = sb.length() - 1;
        while(left <= right) {
            if(Character.toLowerCase(sb.charAt(left)) == Character.toLowerCase(sb.charAt(right))) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
