package leetcode.linkedlist;

/**
 * 小米一面
 *
 * @author: dingaimin
 * @date: 2021/1/7 13:41
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedList {

    /**
     * 方法1：递归
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTowLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTowLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTowLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 方法2：迭代
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTowLists2(ListNode l1, ListNode l2) {
        // 哨兵节点
        ListNode temp = new ListNode(-1);
        ListNode pre = temp;

        // 迭代终止条件是 l1 或 l2 中任意一个遍历完
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                // l1的头结点小于l2的头结点，那么就将 pre.next 指向它，同时移动小的那个链表
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            // 移动指针
            pre = pre.next;
        }
        // 最后将未遍历完的直接放到 pre 的后面
        pre.next = l1 == null ? l2 : l1;
        // 忽略哨兵节点
        return temp.next;
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
