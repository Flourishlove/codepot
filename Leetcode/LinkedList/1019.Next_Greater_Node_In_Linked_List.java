
 // list中下个更大的值
 // 核心思想是用stack存下当前序号，直到遇到下一个更大的值，不断pop出之前存储的序号
 // 类比lc503.next_greater_element
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        Deque<Integer> sk = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();

        while(head != null){
            list.add(head.val);
            head = head.next;
        }

        int[] result = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            while(!sk.isEmpty() && list.get(sk.peek()) < list.get(i)) result[sk.pop()] = list.get(i);
            sk.push(i);
        }
        return result;
    }
}