package leetcode.linkedlist;

/**
 * 相交链表
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * @author: dingaimin
 * @date: 2021/3/16 16:16
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 双指针，当其中一个到末尾时，重新从另一个链表开始遍历，当两个都遍历对方时，相遇的地方就是公共节点
        ListNode a = headA;
        ListNode b = headB;
        while(a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
