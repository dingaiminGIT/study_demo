package leetcode.linkedlist;

/**
 * 对链表进行插入排序
 * https://leetcode-cn.com/problems/insertion-sort-list/
 *
 * @author: dingaimin
 * @date: 2021/2/9 11:11
 */
public class InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // lastSorted 为链表的已排序部分的最后一个节点；cur为待插入的元素
        ListNode lastSorted = head, cur = head.next;

        // 遍历待插入部分的链表
        while (cur != null) {
            // 比较 lastSorted 与 cur 的值，如果小于cur的值，说明已经排好序了，直接往后移动lastSorted指针即可
            if (lastSorted.val <= cur.val) {
                lastSorted = lastSorted.next;
            } else {
                // 需要从排好序的链表头开始遍历，找到合适的插入位置
                ListNode pre = dummy;
                while (pre.next.val <= cur.val) {
                    pre = pre.next;
                }
                // 找到了合适的位置，插入
                lastSorted.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            cur = lastSorted.next;
        }
        return dummy.next;
    }
}
