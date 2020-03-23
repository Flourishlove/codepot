// 从先序和中序遍历中重构二叉树
// 核心思想是递归，对每个出现在先序遍历的节点，找到中序相应的位置，中序里面该节点左边的作为左子树，右边的作为右子树
// 注意在构建右子树的时候，先序的节点要跳过所有左子树的节点，就是根据中序里面左子树的节点数量，来确定先序中右子树开始的节点

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, 0, inorder.length-1);
    }
    public TreeNode helper(int[] preorder, int[] inorder, int curindex, int start, int end){
        if(curindex >= preorder.length || start > end) return null;
        TreeNode cur = new TreeNode(preorder[curindex]);
        int nodeIndex = 0;
        for(int i = start; i <= end; i++){
            if(cur.val == inorder[i]){
                nodeIndex = i;
                break;
            }
        }

        int leftNum = nodeIndex-start;
        cur.left = helper(preorder, inorder, curindex + 1, start, nodeIndex-1);
        cur.right = helper(preorder, inorder, curindex + leftNum + 1, nodeIndex+1, end);
        return cur;
    }
}