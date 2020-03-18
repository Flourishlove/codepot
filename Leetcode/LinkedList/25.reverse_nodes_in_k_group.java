/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 重要题型，几个点要注意
// 1 核心思想是不断找到每个k-group的tail
// 2 两个while循环，外层while(true), 内层找到tail，
// 3 对于reverse类的问题，都需要一个temp节点开始
// 4 结尾记得head已经在末尾了，可以利用来遍历下一个循环

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode tail = dummy;

        int count;
        while(true){
            count = k;
            while(count > 0 && tail != null){
                tail = tail.next;
                count--;
            }
            if(tail == null) break;

            ListNode temp = null;
            while(pre.next != tail){
                temp = pre.next;
                pre.next = pre.next.next;
                temp.next = tail.next;
                tail.next = temp;
            }
            pre = head;
            tail = head;
            head = pre.next;
        }

        return dummy.next;
    }
}