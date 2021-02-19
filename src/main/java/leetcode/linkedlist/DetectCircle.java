package leetcode.linkedlist;

/**
 * 如果链表有环，找到环的入口
 *
 * https://www.nowcoder.com/practice/6e630519bf86480296d0f1c868d425ad?tpId=117&&tqId=34924&&companyId=898&rp=1&ru=/company/home/code/898&qru=/ta/job-code-high/question-ranking
 *
 *  证明过程
 *  https://blog.nowcoder.net/n/c42a259697014745b1688f9c6795d206?f=comment
 *
 * @author: dingaimin
 * @date: 2021/1/16 22:14
 */
public class DetectCircle {

    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;

        // 两指针分别在链表头和相遇位置，并用相同的速度走，两个指针会在环的地方相遇，可以证明出来
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // 利用快慢指针找到相遇点
            if(fast == slow) {
                // 相同速度的节点slow2从头开始，slow 从相遇节点开始，slow 与 slow2 相同的速度，相遇的地方即使环的入口
                ListNode slow2 = head;
                while(slow2 != slow) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
}
