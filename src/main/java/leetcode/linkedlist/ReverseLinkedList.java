package leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
    String str = "";
        String[] s = str.split(" ");
        StringBuffer st2r = new StringBuffer();
        StringBuffer strBuffer = new StringBuffer();

        Map<String, Integer> map = new HashMap<>();

        Set<String> strings = map.keySet();


    }

    // 迭代
    public ListNode reverseListB(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // 迭代
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            // 记录当前节点的下一个节点，暂存后继节点
            ListNode tmp = cur.next;
            // 将当前节点指向 pre，修改next引用指向
            cur.next = pre;
            // pre 和 cur 前进一步
            pre = cur;// pre暂存cur
            cur = tmp;// cur指向下一个阶段
        }
        return pre;
    }

    // 递归
    public ListNode reverseList2(ListNode head) {
        if(head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
