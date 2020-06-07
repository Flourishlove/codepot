import java.io.*;
import java.util.*;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) return 0;
        char[] chs = s.toCharArray();

        int max = 0;
        int start = 0;
        Deque<Integer> sk = new LinkedList<>();
        for(int i = 0; i < chs.length; i++){
            if(chs[i] == '(') sk.push(i);
            else{
                if(sk.isEmpty()) start = i+1;
                else{
                    int temp = sk.pop();
                    if(sk.isEmpty()) max = Math.max(max, i-start+1);
                    else max = Math.max(max, i-sk.peek());
                }
            }
        }
        return max;
    }
}

public class running_template{
    public static void main(String args[]){
        Solution sol = new Solution();
        System.out.println(sol.longestValidParentheses("((())()())"));
    }
}





// public class Main{
//     public static void main(String args[]){
//         Scanner cin = new Scanner(System.in);
//         int a, b;
//         while(cin.hasNextInt()){
//             a = cin.nextInt();
//             b = cin.nextInt();
//             System.out.println(a + b);
//         }
//     }
// }
