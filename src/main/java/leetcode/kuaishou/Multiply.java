package leetcode.kuaishou;

/**
 * 有两个单链表，链表每个节点有一个0-9的数字，整个链表可以看做一个大整数，编程实现两个链表的乘法，
 * 返回一个新的链表包含乘积（注意链表长度可能会较长，直接将链表转数字进行乘法可能会溢出）
 *
 * @author: dingaimin
 * @date: 2021/1/24 17:24
 */
public class Multiply {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(8);
        ListNode node4 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        ListNode node5 = new ListNode(9);
        ListNode node6 = new ListNode(7);
        ListNode node7 = new ListNode(7);
        node5.next = node6;
        node6.next = node7;
        node7.next = null;

        ListNode resNode = multiply(node1, node5);
        while (resNode != null) {
            System.out.print(resNode.val);
            resNode = resNode.next;
        }
    }

    public static ListNode multiply(ListNode l1, ListNode l2) {
        // 先遍历链表将链表的值放到字符串中
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        while (l1 != null) {
            sb1.append(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            sb2.append(l2.val);
            l2 = l2.next;
        }

        String str1 = sb1.toString();
        String str2 = sb2.toString();

        String res = multiply(str1, str2);
        ListNode head = new ListNode(0);
        ListNode pre = new ListNode(res.charAt(0) - '0');
        head.next = pre;
        // 将结果放到新的链表中
        for (int i = 1; i < res.length(); i++) {
            int val = res.charAt(i) - '0';
            ListNode node = new ListNode(val);
            if (i == res.length() - 1) {
                node.next = null;
            }
            pre.next = node;
            pre = node;
        }
        return head.next;
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }

    public static String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
}
