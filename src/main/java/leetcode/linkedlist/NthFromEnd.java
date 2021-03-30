package leetcode.linkedlist;


/**
 *  链表中倒数第k个节点
 *  https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 *
 *  输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，
 *  即链表的尾节点是倒数第1个节点。
 *  例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 *  优秀解法 https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/solution/mian-shi-ti-22-lian-biao-zhong-dao-shu-di-kge-j-11/
 *
 * @author: dingaimin
 * @date: 2021/1/17 14:23
 */
public class NthFromEnd {

    /**
     * 双指针
     * 起初两个指针都指向 head
     * 构建双指针距离：前指针 former 先向前走 k 步，结束后，former 和 latter双指针间相距 k 步
     * 双指针共同移动：循环中，双指针每轮都向前走一步，直到前面那个指针 former 走过链表尾节点，跳出。
     * 跳出后，后面那个节点latter与尾结点的距离就是 k-1 ,即 latter 指向倒数第k 个节点
     *
     * 返回值：返回 latter即可
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;

    }
}
