/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

// 把BST inplace变成双向链表，运用中序遍历的方法，只是把结果加入到list变成节点之间的互相连接
class Solution {
    public Node treeToDoublyList(Node root) {
        if(root == null) return root;
        Node result = new Node(0);
        Node cur = result;

        Stack<Node> sk = new Stack<>();
        Node p = root;
        while(p != null || !sk.isEmpty()){
            if(p != null){
                sk.push(p);
                p = p.left;
            }else{
                p = sk.pop();
                cur.right = p;
                p.left = cur;
                cur = p;
                p = p.right;
            }
        }
        cur.right = result.right;
        result.right.left = cur;
        return result.right;
    }
}