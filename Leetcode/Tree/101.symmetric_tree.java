// 判断是否为对称树
// 递归思想，递归传入的参数必须是两个节点，而不能是一个
// 左节点右孩子和右节点左孩子配对

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left, root.right);
    }
    public boolean helper(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        else if((p == null && q != null) || (p != null && q == null)) return false;
        else if(p.val != q.val) return false;
        else return helper(p.left, q.right) && helper(p.right, q.left);
    }
}