package leetcode.linkedlist;

/**
 * 82. 删除排序链表中的重复元素 II 与 I的区别是，I会留下重复的数字，但 II要把重复的数字全删掉https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 *
 * 示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 * @author: dingaimin
 * @date: 2021/2/24 15:35
 */
public class DeleteDuplicates2 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            // 初始化时，pre 是指向 dummy 的，cur 指向的是 head,所以比较的是 pre.next.val 与 cur.next.val
            if (pre.next.val != cur.next.val) {
                pre = pre.next;
                cur = cur.next;
            } else {
                // 如果 pre 与 cur 指向的节点的值相同，就不断往后移动 cur，直到 pre 与 cur 指向的节点值不同
                while (cur != null && cur.next != null && pre.next.val == cur.next.val) {
                    // 相等就往后移动指针
                    cur = cur.next;
                }
                // pre 与 cur 指向的节点值不同，将pre的next指针指向 cur 的 next
                pre.next = cur.next;
                // 移动 cur 指针
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
