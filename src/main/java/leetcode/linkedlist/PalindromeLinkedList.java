package leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 *
 * @author: dingaimin
 * @date: 2021/1/16 15:41
 */
public class PalindromeLinkedList {

    /**
     * 方法1：将链表中的值复制到数组中 + 双指针比对
     *
     * 遍历链表，将链表中的数复制到数组中，然后通过双指针，分别从头和尾遍历数组，并进行比对，如果多相同就是回文链表，只要存在不同的就不是回文链表
     * 这种方法时间复杂度和空间复杂度都是 O(n)
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();

        // 遍历链表，将链表中的数复制到数组中
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        // 遍历数组，双指针对比
        int p1 = 0;
        int p2 = list.size() - 1;
        while (p1 < p2) {
            if (!list.get(p1).equals(list.get(p2))) {
                return false;
            }
            p1++;
            p2--;
        }
        return true;
    }

    /**
     * 方法2：快慢指针+翻转链表后半部分
     *
     * 将链表后半部分翻转这里涉及到翻转链表，既可以用递归，又可以用迭代，然后对前半部分与后半部分做对比，判断是否是回文链表
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return true;
        }
        // 找到中间节点
        ListNode midNode = findMidNode(head);
        // 反转链表的后半部分
        ListNode half = reverseListNode(midNode.next);
        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = half;
        boolean res = true;
        while (res && p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 为了保证原始链表的完整性，将后半部分再反转回去
        midNode.next = reverseListNode(half);
        return res;
    }

    /**
     * 通过快慢指针找到中间节点
     *
     * @param head
     * @return
     */
    private static ListNode findMidNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 反转链表 - 迭代法
     *
     * @param head
     * @return
     */
    private static ListNode reverseListNode(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            // 记录当前节点的下一个节点
            ListNode tmp = cur.next;
            // 将当前节点指向 pre
            cur.next = pre;
            // pre 和 cur 前进一步
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
