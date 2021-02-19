package leetcode.linkedlist;

/**
 * 链表排序
 * https://leetcode-cn.com/problems/sort-list/
 *
 * 参考解法 https://leetcode-cn.com/problems/sort-list/solution/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/
 *
 * @author: dingaimin
 * @date: 2021/2/9 9:57
 */
public class SortListNode {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;

        SortListNode test = new SortListNode();
        ListNode res = test.sortList(n1);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    /**
     * 归并排序（递归）
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        // 递归终止条件
        if(head == null || head.next == null) {
            return head;
        }
        // 分隔
        // 1.找链表中点，并从中点将链表断开
        // 用快慢指针找中点，注意这里如果fast和slow同时指向 head，最后剩2个节点时，就分不开了，所以让fast先走一步
        ListNode fast = head.next,slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode midNext = slow.next;
        // 从中点将链表断开
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(midNext);

        // 合并
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }

        // left 或 right 可能存在剩余，直接放到 h 的next
        h.next = left != null ? left : right;
        return res.next;
    }
}
