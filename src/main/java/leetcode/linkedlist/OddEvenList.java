package leetcode.linkedlist;

/**
 * 奇偶链表
 * https://leetcode-cn.com/problems/odd-even-linked-list/
 *
 * @author: dingaimin
 * @date: 2021/2/6 22:33
 */
public class OddEvenList {

    /**
     * 分离后节点后合并
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        // 奇数节点的头结点就是链表的头结点即 head
        // 偶数节点的头结点
        ListNode evenHead = head.next;
        // 双指针
        ListNode odd = head, even = head.next;
        while (even != null && even.next != null) {
            // 每一步先更新奇数节点，再更新偶数节点
            // 更新奇数节点
            // 更新奇数节点时，奇数节点的后一个节点要指向偶数节点的后一个节点
            // 即 odd.next = even.next，然后移动 odd 到 odd.next，此时odd成了 even 后一个节点
            odd.next = even.next;
            odd = odd.next;
            // 更新偶数节点
            // 更新偶数节点时，偶数节点的后一个节点要指向奇数节点的后一个节点，即 even.next = odd.next，
            // 然后移动 even 到 even.next，此时even成了 odd 后一个节点
            even.next = odd.next;
            even = even.next;
        }
        // 已经完成了奇数和偶数节点的分离
        // 合奇偶链表
        odd.next = evenHead;
        return head;
    }
}
