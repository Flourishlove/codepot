// 给一个整数n，计数全部的搜索二叉树组合
// 本身是属于递归的做法，任何一个节点作为根节点，两边孩子的组合相乘得到结果
// 但是递归过程可以用dp,因为满足三个条件，1.最优子结构；2.无后效性；3.重复子问题

class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                dp[i] += dp[j] * dp[i-j-1];
            }
        }
        return dp[n];
    }
}