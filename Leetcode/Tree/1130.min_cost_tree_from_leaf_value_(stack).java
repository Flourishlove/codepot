// 给一个数组作为叶子节点，找到中间节点代价最小的值
// 核心思想基于的前提是，为了最小的代价，一定是相邻的最小的值先相乘，然后抛弃最小值，因为之后也不需要了
// 所以我们利用stack，如果某个值小于了左右两边的值，那么把这个值乘上左右两边中较小的那个，并且把这个值pop掉，有点类似于蓄水池的类型

class Solution {
    public int mctFromLeafValues(int[] arr) {
        Deque<Integer> sk = new LinkedList<>();
        sk.push(Integer.MAX_VALUE);

        int res = 0;
        for(int n : arr){
            while(!sk.isEmpty() && sk.peek() < n){
                int temp = sk.pop();
                res += temp * Math.min(sk.peek(), n);
            }
            sk.push(n);
        }

        while(sk.size() > 2){
            res += sk.pop() * sk.peek();
        }

        return res;

    }
}