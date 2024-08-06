package leetcode.linkedlist;


/**
 * 两个链表的第一个公共节点
 * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 *
 * 优秀解决 https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/shuang-zhi-zhen-fa-lang-man-xiang-yu-by-ml-zimingm/
 * 我走过你走过的路，必定相遇
 *
 * @author: dingaimin
 * @date: 2021/1/16 23:17
 */
public class FirstCommonListNode {

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
