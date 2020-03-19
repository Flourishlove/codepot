/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 中序遍历，核心思想是不断把左孩子压栈，直到不能压再弹出一个
// 记住一点，要有一个当前节点p，while的判断条件是p != null || !sk.isEmpty()
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> sk = new Stack<>();
        TreeNode p = root;

        while(p != null || !sk.isEmpty()){
            if(p != null){
                sk.push(p);
                p = p.left;
            }
            else{
                p = sk.pop();
                result.add(p.val);
                p = p.right;
            }
        }

        return result;
    }
}