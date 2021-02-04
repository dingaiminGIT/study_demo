package leetcode;

/**
 * 最长公共子序列
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 *
 * @author: dingaimin
 * @date: 2021/1/29 15:46
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        LongestCommonSubsequence t = new LongestCommonSubsequence();
        /*int res = t.longestCommonSubsequence(s1, s2);
        System.out.println(res);*/
        String res = t.LCS(s1, s2);
        System.out.println(res);
    }


    /**
     * 例如：s1="abcde"　　s2= "ace"，求两个字符串的公共子序列，答案是“ace”
     * 1.　S{s1,s2,s3....si} T{t1,t2,t3,t4....tj}
     * 2.　子问题划分
     * (1) 如果S的最后一位等于T的最后一位，则最大子序列就是{s1,s2,s3...si-1}和{t1,t2,t3...tj-1}的最大子序列+1
     * (2) 如果S的最后一位不等于T的最后一位，那么最大子序列就是
     * ① {s1,s2,s3..si}和 {t1,t2,t3...tj-1} 最大子序列
     * ② {s1,s2,s3...si-1}和{t1,t2,t3....tj} 最大子序列
     * 以上两个自序列的最大值
     * 3.　边界
     * 只剩下{s1}和{t1}，如果相等就返回1，不等就返回0
     * 4.　使用一个表格来存储dp的结果
     * 如果 S[i] == T[j] 则dp[i][j] = dp[i-1][j-1] + 1
     * 否则dp[i][j] = max(dp[i][j-1],dp[i-1][j])
     *
     * 作者：shi-wei-hu-bu-gui
     * 链接：https://leetcode-cn.com/problems/longest-common-subsequence/solution/chao-xiang-xi-dong-tai-gui-hua-jie-fa-by-shi-wei-h/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 7ms 击败 91.20%
     * 42.5MB 击败 13.39%
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int[][] dp = new int[s1.length + 1][s2.length + 1];
        for (int i = 1; i < s1.length + 1; i++) {
            for (int j = 1; j < s2.length + 1; j++) {
                // 如果末端相同
                if (s1[i-1] == s2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    // 如果末端不同
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[s1.length][s2.length];
    }

    /**
     * 15ms 击败21.70%
     * 42.2MB 击败 55.28%
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i < text1.length() + 1; i++) {
            for (int j = 1; j < text2.length() + 1; j++) {
                // 如果末端相同
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    // 如果末端不同
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /**
     * 输出最长公共子序列
     *
     * @param text1
     * @param text2
     * @return
     */
    public String LCS(String text1, String text2) {
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int[][] dp = new int[str1.length + 1][str2.length + 1];
        for (int i = 1; i < str1.length + 1; i++) {
            for (int j = 1; j < str2.length + 1; j++) {
                // 如果末端相等
                if (str1[i-1] == str2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int l1 = str1.length, l2 = str2.length;
        while (l1 != 0 && l2 != 0) {
            if (str1[l1-1] == str2[l2-1]) {
                sb.append(str1[l1-1]);
                l1--;
                l2--;
            } else {
                if (dp[l1-1][l2] > dp[l1][l2-1]) {
                    l1--;
                } else {
                    l2--;
                }
            }
        }
        return sb.reverse().toString();
    }
}
