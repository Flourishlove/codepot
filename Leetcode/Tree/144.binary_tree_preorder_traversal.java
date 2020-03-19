/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // 先序遍历，一个stack，核心思想是针对每个节点，先压右孩子，再压左孩子
 // 注意null也是可以压进stack的，pop出来的时候需要判断是不是null
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> sk = new Stack<>();
        sk.push(root);

        while(!sk.isEmpty()){
            TreeNode cur = sk.pop();
            if(cur != null){
                result.add(cur.val);
                sk.push(cur.right);
                sk.push(cur.left);
            }
        }
        return result;
    }
}