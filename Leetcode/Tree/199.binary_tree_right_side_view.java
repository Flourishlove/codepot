// 找出二叉树最右边的path，即从右边能看见的所有节点
// 用BFS, 每层都计数加入queue的节点数,作为判断是否到了每层的最右
// 如果到了最右,那么加入到result中,并且重置count

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);

        int count = 0;
        int curLevel = 1;
        int nextLevel = 0;
        TreeNode cur = null;
        while(!que.isEmpty()){
            cur = que.poll();
            count++;
            if(cur.left != null){
                nextLevel++;
                que.offer(cur.left);
            }
            if(cur.right != null){
                nextLevel++;
                que.offer(cur.right);
            }

            if(count == curLevel){
                result.add(cur.val);
                curLevel = nextLevel;
                nextLevel = 0;
                count = 0;
            }

        }
        return result;
    }
}