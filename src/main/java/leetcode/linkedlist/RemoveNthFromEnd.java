package leetcode.linkedlist;

/**
 * 删除链表的倒数第n个节点
 * https://www.nowcoder.com/practice/f95dcdafbde44b22a6d741baf71653f6?tpId=117&&tqId=34974&&companyId=898&rp=1&ru=/company/home/code/898&qru=/ta/job-code-high/question-ranking
 *
 * 可以借助找到链表倒数第n个节点的解法：双指针
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 *
 * 给定一个链表，删除链表的倒数第n个节点并返回链表的头指针
 * 例如，
 *  给出的链表为:1->2->3->4->5, n= 2.
 *  删除了链表的倒数第n个节点之后,链表变为1->2->3->5.
 * 备注：
 * 题目保证n一定是有效的
 * 请给出请给出时间复杂度为\ O(n) O(n)的算法
 *
 * @author: dingaimin
 * @date: 2021/1/17 20:59
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd (ListNode head, int n) {
        if(head == null) {
            return null;
        }
        // 先找到倒数第n个节点
        ListNode pre = head;
        ListNode post = head;
        for(int i = 0; i < n; i++) {
            // n 大于链表长度，直接返回
            if(pre == null) {
                return null;
            }
            pre = pre.next;
        }

        // pre 与 post 指针一起走，同时用一个指针记录 post 的前驱节点
        ListNode temp = null;
        while (pre != null) {
            pre = pre.next;
            temp = post;
            post = post.next;
        }
        // 跳出循环后，post 就是倒数第 n 个节点
        // 然后删除倒数第n个节点
        if(temp != null) {
            temp.next = post.next;
        } else {
            // 当 temp 是null，说明倒数第n个节点是头结点，所以直接返回头结点的下一个节点即可
            return head.next;
        }
        return head;
    }
}
