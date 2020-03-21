// 翻转树
// 方法1：递归；方法2：BFS, 对每个节点，都进行左右互换

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }
}

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;

        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        while(!qu.isEmpty()){
            TreeNode cur = qu.poll();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if(cur.left != null) qu.offer(cur.left);
            if(cur.right != null) qu.offer(cur.right);
        }
        return root;
    }
}