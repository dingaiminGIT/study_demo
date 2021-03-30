package leetcode.linkedlist;

/**
 * 删除链表的节点
 * https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 返回删除后的链表的头节点。
 *
 * @author: dingaimin
 * @date: 2021/1/16 22:33
 */
public class DeleteListNode {

    public ListNode deleteNode(ListNode head, int val) {
        // 对头结点特殊处理
        if(head.val == val) {
            return head.next;
        }
        ListNode cur = head;
        // 链表删除节点，要记录待删除节点的前驱节点
        ListNode pre = new ListNode(0);
        pre.next = cur;
        while(cur != null) {
            if(cur.val == val) {
                // 删除节点
                pre.next = cur.next;
                return head;
            } else {
                cur = cur.next;
                pre = pre.next;
            }
        }
        return head;
    }
}
