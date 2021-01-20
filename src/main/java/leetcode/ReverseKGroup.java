package leetcode;

/**
 * 每 k 个一组翻转链表
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * 优秀解法 https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @author: dingaimin
 * @date: 2021/1/17 21:24
 */
public class ReverseKGroup {

    /**
     * 链表分区为已翻转部分+待翻转部分+未翻转部分
     * 每次翻转前，要确定翻转链表的范围，这个必须通过 k 次循环来确定
     * 需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
     * 初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
     * 经过k此循环，end 到达末尾，记录待翻转链表的后继 next = end.next
     * 翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环
     * 特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可
     * 时间复杂度为 O(n*K) 最好的情况为 O(n) 最差的情况未 O(n^2)
     * 空间复杂度为 O(1) 除了几个必须的节点指针外，我们并没有占用其他空间
     *
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 给链表的头结点设置一个前驱节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // pre 为待翻转链表的前驱节点
        ListNode pre = dummy;
        // end 为待翻转链表的尾结点，通过k次循环到待翻转链表的末尾
        ListNode end = dummy;

        while(end.next != null) {
            // 每次翻转前，要确定翻转链表的范围，通过 k 次循环来确定
            for(int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if(end == null) {
                break;
            }
            // start 为待翻转链表的头结点
            ListNode start = pre.next;
            // next 为待翻转链表的后继节点
            ListNode next = end.next;
            // 先将待翻转链表的后继节点置为null，这样就可以利用翻转一个链表的方法来翻转待翻转链表了
            end.next = null;
            pre.next = reverse(start);

            // 将三部门链表拼接起来
            // 链表翻转后，start 为翻转后的链表的尾结点了，所以将其指向待翻转链表的头结点
            start.next = next;

            // 重置节点为下次循环做准备
            // pre 为下一组待翻转链表的前驱节点即已经翻转后的尾节点 start
            pre = start;
            // end 节点也先指向前驱节点
            end = pre;
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            // 记录下一个节点
            ListNode next = cur.next;
            // 翻转当前节点
            cur.next = pre;
            // pre 和 cur 前进一步
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
