// 思想是快慢两个指针，让快的先走n步，用慢的节点找到需要删除的节点的前一个节点
// 需要注意的如果是删除第一个节点，slow从head出发无法处理，所以建立一个dummy，从dummy出发可以实现统一处理
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        while(n >= 0){
            fast = fast.next;
            n--;
        }
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        if(slow.next != null){
            slow.next = slow.next.next;
        }

        return dummy.next;
    }
}