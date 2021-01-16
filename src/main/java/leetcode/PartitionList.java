package leetcode;

/**
 * 分隔链表
 * https://leetcode-cn.com/problems/partition-list/
 *
 * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 输入：head = 1->4->3->2->5->2, x = 3
 * 输出：1->2->2->4->3->5
 *
 * @author: dingaimin
 * @date: 2021/1/16 16:15
 */
public class PartitionList {

    /**
     * 维护两个链表 small 和 big
     * small用于存放所有小于x的值，big 用于存放所有大于等于x的值
     * 遍历链表，根据与x的大小，分别放到对应的链表中
     * 最后将small和big合并起来，并返回small的有效头结点
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(-1);
        ListNode smallHead = small;
        ListNode big = new ListNode(-1);
        ListNode bigHead = big;

        while (head != null) {
            if (head.val < x) {
                small.next = new ListNode(head.val);
                small = small.next;
            } else {
                big.next = new ListNode(head.val);
                big = big.next;
            }
            head = head.next;
        }
        small.next = bigHead.next;
        return smallHead.next;

    }
}
