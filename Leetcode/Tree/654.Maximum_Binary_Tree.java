// 构建最大二叉树，要求把最大值作为根节点，左右区间同样方法构建子树作为左右孩子
// 核心思想，用stack maintain最右的从根节点往下的path
// 类似于next greater number，stack里面是当前的树，当遇到下一个更大的值时，不断pop，作为当前的左孩子;
// 如果当前值小于sk.peek(),则作为sk.peek()的右孩子

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> sk = new LinkedList<>();

        for(int i = 0; i < nums.length; i++){
            TreeNode cur = new TreeNode(nums[i]);
            while(!sk.isEmpty() && sk.peek().val < nums[i]){
                cur.left = sk.pop();
            }
            if(!sk.isEmpty()) sk.peek().right = cur;
            sk.push(cur);
        }
        return sk.isEmpty() ? null : sk.pollLast();
    }
}