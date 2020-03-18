// PriorityQueue经典题目，需要注意构建比较器时不能直接使用减法，会溢出
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b){
                if(a.val > b.val) return 1;
                else if(a.val < b.val) return -1;
                else return 0;
            }
        });

        for(ListNode node : lists){
            if(node != null) pq.add(node); // 某些list为空的情况
        }

        ListNode result = new ListNode(0);
        ListNode cur = result;
        while(!pq.isEmpty()){
            cur.next = pq.poll();
            cur = cur.next;
            if(cur.next != null) pq.add(cur.next);
        }

        return result.next;
    }
}