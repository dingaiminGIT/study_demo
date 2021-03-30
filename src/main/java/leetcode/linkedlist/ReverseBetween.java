package leetcode.linkedlist;

/**
 *  反转链表 m 到 n 的部分
 *  https://leetcode-cn.com/problems/reverse-linked-list-ii/
 *
 *  反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @author: dingaimin
 * @date: 2021/1/20 17:49
 */
public class ReverseBetween {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node = new ListNode(5);
        head.next = node;
        node.next = null;

        ListNode ret = reverseBetween(head, 1, 2);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }
    }

    /**
     * 待翻转链表前面的链表 + 待翻转链表 + 待翻转链表的后面的链表
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        // 给链表头设置一个前驱节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 记录待翻转链部分的前驱节点
        ListNode pre = dummy;
        pre.next = head;

        ListNode cur = head;
        int count = 1;
        while(count < m) {
            cur = cur.next;
            pre = pre.next;
            count++;
        }
        // 待翻转链表的头节点
        ListNode toReverseNodeHead = cur;
        // 找到了第m个节点的前驱节点

        // 找n节点，并记录 next
        while(count < n){
            cur = cur.next;
            count++;
        }
        // 找到了第n个节点
        // 找到第n个节点的后继节点
        // 记录待翻转部分的后继节点
        ListNode next = cur.next;
        // 将待翻转链表与后继节点断开链接
        cur.next = null;

        // 翻转待翻转链表
        ListNode node = reverse(toReverseNodeHead);
        // 重新将三部分链接起来
        pre.next = node;
        toReverseNodeHead.next = next;

        return dummy.next;
    }

    // 反转单链表
    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode temp = cur.next;
            // 反转当前节点
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
