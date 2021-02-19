package leetcode.linkedlist;

/**
 * 删除排序链表中的重复元素
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 *
 * @author: dingaimin
 * @date: 2021/2/9 21:54
 */
public class DeleteDuplicates {

    /**
     * 1ms  32.36%
     * 37.9MB 48.32%
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre  = new ListNode(0);
        ListNode cur = head;
        pre.next = cur;
        while(cur != null && cur.next != null) {
            while(cur != null && cur.next != null && cur.val == cur.next.val) {
                // 删除节点
                cur.next = cur.next.next;
            }
            cur = cur.next;
            pre = pre.next;
        }
        return head;
    }

    /**
     * 0ms 100.00%
     * 37.7MB 87.04%
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode cur = head;
        while(cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
