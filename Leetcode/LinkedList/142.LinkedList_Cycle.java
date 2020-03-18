// 找出链表中环的开头，重点是要记得快走的节点走的总路程是慢的两倍关系
// 从而推导出相遇之后，慢的节点继续到环的开头所需路程和从起点出发到环的开头路程相同
// (x+r+y) = 2(x+y), 其中r是环的长度

public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }
        if(fast == null || fast.next == null) return null;

        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}