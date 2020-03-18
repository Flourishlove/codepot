/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode result = head.next;
        ListNode pre = new ListNode(0);
        ListNode first = head;
        ListNode second = head.next;
        while(second != null){
            pre.next = second;
            first.next = second.next;
            second.next = first;
            pre = first;
            // 容易出错的地方在于不能连续用first = first.next; first = first.next.next;因为first已经改变了
            first = first.next;
            second = first == null ? null : first.next;
        }
        return result;
    }
}