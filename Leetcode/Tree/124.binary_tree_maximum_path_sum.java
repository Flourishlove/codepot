// 找出二叉树中和最大的路径（任意路径）
// 核心思想是递归，对于每个节点，返回一个从该节点往下的最大求和值。同时维护一个全局最大的路径和。
// 对于每个节点，更新全局变量，比较是全局变量大还是当前节点值加上左右孩子的总和大
// 需要注意的是，对于每个节点的左右孩子，需要判断它是不是大于0，如果小于0，意味着需要放弃这个孩子，直接取零

class Solution {
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        int[] result = new int[1];
        result[0] = Integer.MIN_VALUE;
        helper(root, result);
        return result[0];
    }

    public int helper(TreeNode node, int[] result){
        if(node == null) return 0;
        int left = Math.max(0, helper(node.left, result));
        int right = Math.max(0, helper(node.right, result));
        result[0] = Math.max(result[0], left + right + node.val);
        return node.val + Math.max(left, right);
    }
}