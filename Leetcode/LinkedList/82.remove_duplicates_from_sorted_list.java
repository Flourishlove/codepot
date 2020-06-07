// 把重复节点去掉，核心思想是针对每个节点，都和前后两个节点比较，都不相同才是unique的节点；
// 然后用一个unique指针把这些unique节点串起来
// 另外要注意处理末尾

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.val = head.val == 0 ? 1 : 0;
        ListNode pre = dummy;
        ListNode cur = head;
        ListNode unique = dummy;

        while(cur != null && cur.next != null){
            if(cur.val != pre.val && cur.val != cur.next.val){
                unique.next = cur;
                unique = unique.next;
            }
            pre = cur;
            cur = cur.next;
        }

        if(cur.val != pre.val){
            unique.next = cur;
        }
        else{
            unique.next = null;
        }

        return dummy.next;
    }
}