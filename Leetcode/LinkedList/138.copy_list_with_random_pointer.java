/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/


// deepcopy出来的节点直接跟在原来节点后面，全部生成后再拆分出来
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return head;

        Node cur = head;
        while(cur != null){
            Node temp = new Node(cur.val);
            temp.next = cur.next;
            cur.next = temp;
            cur = cur.next.next;
        }

        cur = head;
        while(cur != null){
            if(cur.random != null) cur.next.random = cur.random.next;
            cur = cur.next.next;
        }

        cur = head.next;
        Node first = head;
        Node second = head.next;
        while(second.next != null){
            first.next = first.next.next;
            second.next = second.next.next;
            first = first.next;
            second = second.next;
        }
        first.next = null;

        return cur;
    }
}
