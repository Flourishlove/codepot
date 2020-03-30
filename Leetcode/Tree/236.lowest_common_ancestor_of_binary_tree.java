// 寻找最近公共祖先，核心思想是递归
// 1.如果根节点等于两个节点中的一个，直接返回;
// 2.如果两个子树都有返回值，那么返回根节点；
// 3.如果两个子树中只有一个有返回，那么返回那个节点

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null && right == null) return null;
        else if(left != null && right != null) return root;
        else return left == null ? right : left;
    }
}