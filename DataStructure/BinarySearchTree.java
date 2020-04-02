class Node {
    private Node left = null;
    private Node right = null;
    private int val;
    public Node(int val){
        this.val = val;
    }
}

public class BinarySearchTree {
    private Node root;
    public BinarySearchTree(){
        this.root = null;
    }

    public boolean find(int val){
        Node cur = root;
        while(cur != null){
            if(cur.val == val) return true;
            else if(val < cur.val) cur = cur.left;
            else cur = cur.right;
        }
        return false;
    }

    // 一定会插入到叶子节点上
    public void insert(int val){
        Node temp = new Node(val);
        if(root == null){
            root = temp;
            return;
        }
        Node cur = root;
        Node parent = null;
        while(true){
            parent = cur;
            if(val < cur.val){
                cur = cur.left;
                if(cur == null){
                    cur = temp;
                    parent.left = cur;
                    return;
                }
            }
            else{
                cur = cur.right;
                if(cur == null){
                    cur = temp;
                    parent.right = cur;
                    return;
                }
            }
        }
    }

    // three cases: no child, one child and two child
    public boolean delete(int val){
        if(root == null) return false;
        Node parent = root;
        Node cur = root;
        boolean isLeftChild = false;

        while(cur.val != val){
            parent = cur;
            if(val < cur.val){
                cur = cur.left;
                isLeftChild = true;
            }
            else{
                cur = cur.right;
                isLeftChild = false;
            }
            if(cur == null) return false;
        }

        if(cur.left == null && cur.right == null){
            if(cur == root) root = null;
            else if(isLeftChild) parent.left = null;
            else parent.right = null;
        }
        else if(cur.left == null){
            if(cur == root) root = root.right;
            else if(isLeftChild) parent.left = cur.right;
            else parent.right = cur.right;
        }
        else if(cur.right == null){
            if(cur == root) root = root.left;
            else if(isLeftChild) parent.left = cur.left;
            else parent.right = cur.left;
        }
        else{
            Node successor = findSuccessor();
            if(cur == root){
                root = successor;
            }
            else if(isLeftChild) parent.left = successor;
            else parent.right = successor;
        }
        return true;
    }

    public Node findSuccessor(Node deleteNode){
        Node successor = null;
        Node successorParent = null;
        Node cur = deleteNode.right;
        while(cur != null){
            successorParent = successor;
            successor = cur;
            cur = cur.left;
        }
        if(successor != deleteNode.right){
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
            successor.left = deleteNode.left;
        }
        return successor;
    }

    // display in an incremental order
    public List<Integer> display(Node root){
        List<Integer> result = new ArrayList<>();
        if(root != null){
            display(root.left);
            result.add(root.val);
            display(root.right);
        }
        return result;
    }

}