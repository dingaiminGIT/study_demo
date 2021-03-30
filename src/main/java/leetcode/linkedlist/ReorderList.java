package leetcode.linkedlist;

/**
 * 重排链表
 * https://leetcode-cn.com/problems/reorder-list/
 *
 * @author: dingaimin
 * @date: 2021/2/5 19:03
 */
public class ReorderList {

    /**
     * 找链表中点 + 链表逆序 + 合并链表
     * 目标链表是将原链表的左半端和反转后的右半端合并后的结果
     * 1.找到原链表的中点（可以使用快慢指针 https://leetcode-cn.com/problems/middle-of-the-linked-list/）
     * 2.将原链表的右半部分反转（https://leetcode-cn.com/problems/reverse-linked-list/）
     * 3.合并两个链表
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        // 找到链表的中点
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        // 将 mid 节点的后继指针指向null
        mid.next = null;
        // 反转右半部分链表
        l2 = reverseNode(l2);
        mergeList(l1, l2);
    }

    /**
     * 找链表的中间节点--双指针
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseNode(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 合并链表
     *
     * @param l1
     * @param l2
     */
    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1Temp, l2Temp;
        while (l1 != null && l2 != null) {
            l1Temp = l1.next;
            l2Temp = l2.next;

            l1.next = l2;
            l1 = l1Temp;

            l2.next = l1;
            l2 = l2Temp;
        }
    }
}
