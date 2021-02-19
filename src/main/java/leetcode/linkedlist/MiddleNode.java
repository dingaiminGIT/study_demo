package leetcode.linkedlist;


/**
 * 链表的中间节点
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 *
 * @author: dingaimin
 * @date: 2021/2/5 15:39
 */
public class MiddleNode {

    public ListNode middleNode(ListNode head) {
        ListNode cur = head;
        int n = 0;
        while(cur != null) {
            n++;
            cur = cur.next;
        }
        int k = 0;
        cur = head;
        while(k < n/2) {
            k++;
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 快慢指针
     * fast与slow都指向head，slow每次移动1步，fast每次移动2步，当fast走到链表的末尾时，slow就是中间节点
     *
     * @param head
     * @return
     */
    public ListNode middleNode2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
