package leetcode;

/**
 * 比较版本号
 * https://leetcode-cn.com/problems/compare-version-numbers/
 *
 * @author: dingaimin
 * @date: 2021/1/29 15:27
 */
public class CompareVersion {

    public static void main(String[] args) {
        CompareVersion version = new CompareVersion();
        int res = version.compareVersion("1.0.1", "1");
        System.out.println(res);
    }

    /**
     * 按照 . 对版本号进行切割，然后依次比较每个数字，这里注意需要对 . 进行转义
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < nums1.length || j < nums2.length) {
            // 当一个已经遍历结束的话，赋值成0
            String num1 = i < nums1.length ? nums1[i] : "0";
            String num2 = j < nums2.length ? nums2[j] : "0";
            int res = compare(num1, num2);
            if (res == 0){
                i++;
                j++;
            } else {
                return res;
            }
        }
        return 0;
    }

    private int compare(String num1, String num2) {
        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        if (n1 > n2) {
            return 1;
        } else if (n1 < n2) {
            return -1;
        } else {
            return 0;
        }
    }
}
