package leetcode.linkedlist;

/**
 * 24. 两两交换链表中的节点 中等难度
 * https://leetcode.cn/problems/swap-nodes-in-pairs/description/
 */
public class SwapPairs {

    /**
     * 递归
     * 终止条件：链表中没有节点或者链表中只有一个节点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        // 递归终止条件:链表中没有节点或者链表中只有一个节点
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        // 交换
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    /**
     * 迭代：
     *
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = dummy;

        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummy.next;
    }
}
